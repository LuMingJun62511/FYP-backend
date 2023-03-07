package com.example.FYP_backend.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "ums_member")
public class UmsMember {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", length = 64)
    private String username;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "nickname", length = 64)
    private String nickname;

    @Column(name = "phone", length = 64)
    private String phone;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "icon", length = 500)
    private String icon;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "city", length = 64)
    private String city;

    @Column(name = "personalized_signature", length = 200)
    private String personalizedSignature;

    @Column(name = "loyal_points")
    private Integer loyalPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public Integer getLoyalPoints() {
        return loyalPoints;
    }

    public void setLoyalPoints(Integer loyalPoints) {
        this.loyalPoints = loyalPoints;
    }

}
