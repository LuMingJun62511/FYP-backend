package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
public class PaymentController {
    @Value("${STRIPE_sk}")
    private String STRIPE_sk;

    private static Gson gson = new Gson();

    @Resource
    private PmsAbstractProductRepository productRepo;

    static class CreatePayment {
        @SerializedName("products")
        List<CartItem> products;

        public List<CartItem> getProducts() {
            return products;
        }
    }

    static class CartItem {
        @SerializedName("productID")
        int productID;

        @SerializedName("amount")
        int amount;

        @SerializedName("price")
        BigDecimal price;

        public int getProductID() {
            return productID;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
        public String getClientSecret() {
            return clientSecret;
        }
    }

    int calculateOrderAmount(List<CartItem> items) {
        int totalAmount = 0;
        for (CartItem item : items) {
            PmsAbstractProduct pmsAbstractProduct = productRepo.findFirstByIdEquals(item.getProductID());
            if (item.getAmount() < 0 ) {
                item.setAmount(0);
                System.out.println("amount changed");
            }
            if(!Objects.equals(pmsAbstractProduct.getPrice(), item.getPrice())) {
                item.setPrice(pmsAbstractProduct.getPrice());
                System.out.println("Price changed");
            }
            System.out.println("match");
            BigDecimal total = item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())).multiply(BigDecimal.valueOf(100));
            totalAmount += total.intValue();
        }
        if(totalAmount < 50){
            totalAmount += 5;
        }

        return totalAmount;
    }


    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody CreatePayment postBody) throws StripeException {
        Stripe.apiKey = STRIPE_sk;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(new Long(calculateOrderAmount(postBody.getProducts())))
                        .setCurrency("eur")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return gson.toJson(paymentResponse);
    }
}
