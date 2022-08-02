package com.duong.productmanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private double amount;

    @NotBlank
    private int quantity;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Product product;

    public OrderDetails() {
    }

    public OrderDetails(Long id, double amount, int quantity, Orders orders, Product product) {
        this.id = id;
        this.amount = amount;
        this.quantity = quantity;
        this.orders = orders;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", orders=" + orders +
                ", product=" + product +
                '}';
    }
}
