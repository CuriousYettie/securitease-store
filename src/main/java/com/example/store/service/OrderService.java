package com.example.store.service;

import com.example.store.dto.OrderDTO;
import com.example.store.entity.Order;
import com.example.store.exceptions.InvalidOrderException;
import com.example.store.exceptions.OrderNotFoundException;
import com.example.store.mapper.OrderMapper;
import com.example.store.repository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    public List<OrderDTO> getAllOrders() {
        return orderMapper.ordersToOrderDTOs(orderRepository.findAll());
    }

    public OrderDTO getOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
        return orderMapper.orderToOrderDTO(order);
    }

    public OrderDTO createOrder(Order order) {
        try {
            return orderMapper.orderToOrderDTO(orderRepository.save(order));
        } catch (Exception ex) {
            log.error("Failed to create order: {}", ex.getMessage(), ex);
            throw new InvalidOrderException("Failed to create order. Please try again later.");
        }
    }
}
