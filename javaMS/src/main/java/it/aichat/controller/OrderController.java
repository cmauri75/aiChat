/* Cesare Mauri - Ai MLM Team (C) 2023 */
package it.aichat.controller;

import it.aichat.aiService.OrderManagementAIAssistant;
import it.aichat.controller.dto.PromptRequest;
import it.aichat.entity.OrderEntity;
import it.aichat.service.OrderManagementService;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class OrderController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);
  private final OrderManagementService service;

  private final OrderManagementAIAssistant orderManagementAIAssistant;

  public OrderController(
      OrderManagementService service, OrderManagementAIAssistant orderManagementAIAssistant) {
    this.service = service;
    this.orderManagementAIAssistant = orderManagementAIAssistant;
  }

  @GetMapping("/order")
  public ResponseEntity<List<OrderEntity>> getAllOrders() {
    return ResponseEntity.ok(service.getAllOrders());
  }

  @PostMapping("/order/prompt")
  public ResponseEntity<String> createOrderViaPrompt(@RequestBody PromptRequest promptRequest) {
    ChatResponse response =
        orderManagementAIAssistant.callChatClient(
            Set.of("createOrderFn", "getUserOrdersFn"), promptRequest.prompt());
    String resultContent = response.getResult().getOutput().getContent();
    return ResponseEntity.ok("The response from the LLM service: " + resultContent);
  }
}
