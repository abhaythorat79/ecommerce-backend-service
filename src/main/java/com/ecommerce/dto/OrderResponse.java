package com.ecommerce.dto;

import java.util.List;

public class OrderResponse {
    private Long orderId;
    private double totalAmount;
    private String status;
    private List<String> products;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public OrderResponse(Long orderId, double totalAmount, String status, List<String> products) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.products = products;
    }

    public OrderResponse() {
    }
}
