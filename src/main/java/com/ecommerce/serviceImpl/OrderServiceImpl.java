package com.ecommerce.serviceImpl;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        User user = userRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderItemRequest itemReq : request.getItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice() * itemReq.getQuantity());
            item.setOrder(order);

            total += item.getPrice();
            orderItems.add(item);
        }

        order.setTotalAmount(total);
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setTotalAmount(total);
        response.setStatus(savedOrder.getStatus().name());
        response.setProducts(
                orderItems.stream()
                        .map(i -> i.getProduct().getName())
                        .toList()
        );

        return response;
    }
}
