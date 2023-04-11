package com.example.FYP_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "ums_member_receive_address")
public class UmsMemberReceiveAddress {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone_number", length = 64)
    private String phoneNumber;

    @Column(name = "default_status")
    private Integer defaultStatus;

    @Column(name = "line1", length = 100)
    private String line1;

    @Column(name = "line2", length = 100)
    private String line2;

    @Column(name = "city", length = 100)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private UmsMember member;

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Integer defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
