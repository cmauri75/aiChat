package it.aichat.service;

import it.aichat.entity.OrderEntity;
import it.aichat.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderManagementService {
    @Autowired
    private OrderRepository orderRepository;

    public UUID createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity).getOrderID();
    }

    public Optional<List<OrderEntity>> getAllUserOrders(String userID) {
        return orderRepository.findByUserID(userID);
    }

    public List<OrderEntity> getAllOrders(

    ) {
        return orderRepository.findAll();
    }
}
