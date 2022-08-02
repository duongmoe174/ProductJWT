package com.duong.productmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProfileUser profileUser;

    @NotBlank
    private double amount;

    @NotBlank
    private Date date;

    private int orderNum;

    public Orders(Long id, ProfileUser profileUser, double amount, Date date) {
        this.id = id;
        this.profileUser = profileUser;
        this.amount = amount;
        this.date = date;
    }

    public Orders(Long id, ProfileUser profileUser, double amount, Date date, int orderNum) {
        this.id = id;
        this.profileUser = profileUser;
        this.amount = amount;
        this.date = date;
        this.orderNum = orderNum;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public ProfileUser getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser = profileUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", profileUser=" + profileUser +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
