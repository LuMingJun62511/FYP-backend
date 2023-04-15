package com.example.FYP_backend.Controller;

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

@RestController
public class PaymentController {
    @Value("${STRIPE_sk}")
    private String STRIPE_sk;

    private static Gson gson = new Gson();
    //这是我传进来的一系列物品，也是顾客的购物清单
    static class CreatePayment {
        @SerializedName("items")
        Object[] items;

        public Object[] getItems() {
            return items;
        }
    }
    //这是返回的，似乎是response
    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
        public String getClientSecret() {
            return clientSecret;
        }
    }

    static int calculateOrderAmount(Object[] items) {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // people from directly manipulating the amount on the client
        return 1400;
    }


    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody CreatePayment postBody) throws StripeException {
//        这是逻辑上的第一步，创建了一个paymentIntent，这个paymentIntent是一个对象，里面包含了很多信息，比如金额，货币，支付方式等等，
//        但是我不知道这里有没有问题

        Stripe.apiKey = STRIPE_sk;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
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
