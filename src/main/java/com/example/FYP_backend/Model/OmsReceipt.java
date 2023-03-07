package com.example.FYP_backend.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "oms_receipt")
public class OmsReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private UmsMember member;

    @Column(name = "coupon_id")
    private Integer couponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OmsOrder order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private UmsMemberReceiveAddress receiver;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "pay_amount", precision = 10, scale = 2)
    private BigDecimal payAmount;

    @Column(name = "delivery_amount", precision = 10, scale = 2)
    private BigDecimal deliveryAmount;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "delivery_sn", length = 64)
    private String deliverySn;

    @Column(name = "note", length = 500)
    private String note;

//    @OneToMany(mappedBy = "omsReceipt")
//    @JsonIgnore
//    private Set<OmsReceiptItem> omsReceiptItems = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public OmsOrder getOrder() {
        return order;
    }

    public void setOrder(OmsOrder order) {
        this.order = order;
    }

    public UmsMemberReceiveAddress getReceiver() {
        return receiver;
    }

    public void setReceiver(UmsMemberReceiveAddress receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    public Set<OmsReceiptItem> getOmsReceiptItems() {
//        return omsReceiptItems;
//    }

//    public void setOmsReceiptItems(Set<OmsReceiptItem> omsReceiptItems) {
//        this.omsReceiptItems = omsReceiptItems;
//    }

}
