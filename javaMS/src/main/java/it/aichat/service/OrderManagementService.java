/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat.service;

import it.aichat.entity.OrderEntity;
import it.aichat.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementService {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderManagementService.class);

  private final OrderRepository orderRepository;

  public OrderManagementService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public UUID createOrder(OrderEntity orderEntity) {
    log.info("Creating order: {}", orderEntity);
    return orderRepository.save(orderEntity).getOrderID();
  }

  public Optional<List<OrderEntity>> getAllUserOrders(String userID) {
    return orderRepository.findByUserID(userID);
  }

  public List<OrderEntity> getAllOrders() {

    return orderRepository.findAll();
  }
}
