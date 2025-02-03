/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat.aiService;

import it.aichat.entity.OrderEntity;
import it.aichat.service.OrderManagementService;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
public class OmAiAssistantConfiguration {
  @Bean
  @Description(
      """
      Create an order. The Order ID is identified with orderID should be a valid random uuid.
      The order quantity is identified by orderQuantity.
      The user is identified by userID.
      The order quantity should be a positive whole number.
      If any of the parameters like user id and the order quantity is missing
      then ask the user to provide the missing information.""")
  public Function<CreateOrderRequest, UUID> createOrderFn(
      OrderManagementService orderManagementService) {
    return createOrderRequest ->
        orderManagementService.createOrder(createOrderRequest.orderEntity());
  }

  @Bean
  @Description("get all the orders of an user. The user ID is identified with userID.")
  public Function<GetOrderRequest, List<OrderEntity>> getUserOrdersFn(
      OrderManagementService orderManagementService) {
    return getOrderRequest ->
        orderManagementService.getAllUserOrders(getOrderRequest.userID()).get();
  }

  record GetOrderRequest(String userID) {}

  record CreateOrderRequest(OrderEntity orderEntity) {}
}
