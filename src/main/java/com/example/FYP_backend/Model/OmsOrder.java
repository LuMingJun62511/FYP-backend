package com.example.FYP_backend.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "oms_order")
public class OmsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private UmsMember member;

    @Column(name = "coupon_id")
    private Integer couponId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiver_id", nullable = false)
    private UmsMemberReceiveAddress receiver;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "delivery_amount", precision = 10, scale = 2)
    private BigDecimal deliveryAmount;

    @Column(name = "pay_amount", precision = 10, scale = 2)
    private BigDecimal payAmount;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "loyal_point_gained")
    private Integer loyalPointGained;

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

    public UmsMemberReceiveAddress getReceiver() {
        return receiver;
    }

    public void setReceiver(UmsMemberReceiveAddress receiver) {
        this.receiver = receiver;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLoyalPointGained() {
        return loyalPointGained;
    }

    public void setLoyalPointGained(Integer loyalPointGained) {
        this.loyalPointGained = loyalPointGained;
    }

}
