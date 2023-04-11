package com.example.FYP_backend.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ums_member_statistics_info")
public class UmsMemberStatisticsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private UmsMember member;

    @Column(name = "consume_amount", precision = 10, scale = 2)
    private BigDecimal consumeAmount;

    @Column(name = "order_count")
    private Integer orderCount;

    @Column(name = "coupon_count")
    private Integer couponCount;

    @Column(name = "comment_count")
    private Integer commentCount;

    @Column(name = "return_order_count")
    private Integer returnOrderCount;

    @Column(name = "recent_order_time")
    private Instant recentOrderTime;

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

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getReturnOrderCount() {
        return returnOrderCount;
    }

    public void setReturnOrderCount(Integer returnOrderCount) {
        this.returnOrderCount = returnOrderCount;
    }

    public Instant getRecentOrderTime() {
        return recentOrderTime;
    }

    public void setRecentOrderTime(Instant recentOrderTime) {
        this.recentOrderTime = recentOrderTime;
    }

}
