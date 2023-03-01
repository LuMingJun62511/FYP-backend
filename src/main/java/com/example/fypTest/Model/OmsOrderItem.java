//package com.example.fypTest.Model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "oms_order_item")
//public class OmsOrderItem {
//    @EmbeddedId
//    private OmsOrderItemId id;
//
//    @MapsId("orderId")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "order_id", nullable = false)
//    private OmsOrder order;
//
//    @Column(name = "product_quantity")
//    private Integer productQuantity;
//
//    public OmsOrderItemId getId() {
//        return id;
//    }
//
//    public void setId(OmsOrderItemId id) {
//        this.id = id;
//    }
//
//    public OmsOrder getOrder() {
//        return order;
//    }
//
//    public void setOrder(OmsOrder order) {
//        this.order = order;
//    }
//
//    public Integer getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(Integer productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//
//}
package com.example.fypTest.Model;

import javax.persistence.*;

@Entity
@Table(name = "oms_order_item")
public class OmsOrderItem {
    @EmbeddedId
    private OmsOrderItemId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OmsOrder order;

    @MapsId("abstractProductId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "abstract_product_id", nullable = false)
    private PmsAbstractProduct abstractProduct;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    public OmsOrderItemId getId() {
        return id;
    }

    public void setId(OmsOrderItemId id) {
        this.id = id;
    }

    public OmsOrder getOrder() {
        return order;
    }

    public void setOrder(OmsOrder order) {
        this.order = order;
    }

    public PmsAbstractProduct getAbstractProduct() {
        return abstractProduct;
    }

    public void setAbstractProduct(PmsAbstractProduct abstractProduct) {
        this.abstractProduct = abstractProduct;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

}
