/* Decathlon Italy - Tacos Team(C) 2023 */
package it.aichat.controller;

import it.aichat.entity.OrderEntity;
import it.aichat.service.OrderManagementService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);
    private final OrderManagementService service;

    public OrderController(OrderManagementService service) {
        this.service = service;

    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
