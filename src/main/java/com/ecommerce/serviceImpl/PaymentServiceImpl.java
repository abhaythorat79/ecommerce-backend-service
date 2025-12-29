package com.ecommerce.serviceImpl;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.dto.PaymentResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Payment;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.PaymentStatus;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentRepository;
import com.ecommerce.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public PaymentResponse makePayment(PaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Mock payment success
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.SUCCESS);

        paymentRepository.save(payment);

        // Update order status
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getId());
        response.setStatus(payment.getStatus().name());
        response.setMessage("Payment successful. Order marked as PAID.");

        return response;
    }
}
