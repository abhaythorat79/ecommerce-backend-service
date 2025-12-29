package com.ecommerce.dto;

public class PaymentResponse {
    private Long paymentId;
    private String status;
    private String message;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentResponse(Long paymentId, String status, String message) {
        this.paymentId = paymentId;
        this.status = status;
        this.message = message;
    }

    public PaymentResponse() {
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "paymentId=" + paymentId +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
