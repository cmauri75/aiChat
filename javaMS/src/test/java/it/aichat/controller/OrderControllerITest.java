/* Cesare Mauri - Ai MLM Team (C) 2023 */
package it.aichat.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.aichat.controller.dto.PromptRequest;
import it.aichat.entity.OrderEntity;
import it.aichat.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerITest {
  private static final ObjectMapper mapper = new ObjectMapper();

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderControllerITest.class);

  @Autowired private MockMvc mockMvc;

  @Autowired private OrderRepository orderRepository;

  @Test
  @Transactional
  void search_returns_data() throws Exception {
    var order = Instancio.of(OrderEntity.class).create();
    orderRepository.save(order);
    mockMvc
        .perform(get("/api/v1/order").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].orderID").value(order.getOrderID().toString()))
        .andExpect(jsonPath("$[0].orderQuantity").value(order.getOrderQuantity()))
        .andExpect(jsonPath("$[0].userID").value(order.getUserID()));
  }

  @Test
  void when_ask_create_order_it_creates() throws Exception {
    PromptRequest pr =
        new PromptRequest(
            "Create an order for user id Cesare with quantity 100. Randomly generate UUID value for"
                + " the order ID.");
    mockMvc
        .perform(
            post("/api/v1/order/prompt")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(pr)))
        .andDo(
            result -> log.info("Test call response: {}", result.getResponse().getContentAsString()))
        .andExpect(status().is2xxSuccessful());

    var res = orderRepository.findAll().get(0);
    assertThat(res).isNotNull();
    assertThat(res.getOrderID()).isNotNull();
    assertThat(res.getOrderQuantity()).isEqualTo(100);
    assertThat(res.getUserID()).isEqualTo("Cesare");

    orderRepository.delete(res);
  }

  @Test
  void when_ask_create_two_orders_both_are_created() throws Exception {
    PromptRequest pr =
        new PromptRequest(
            "Create two orders. The first order is for user id Sophia with quantity 30. The second"
                + " order is for user id Mary with quantity 40. Randomly generate positive UUID"
                + " values for the order IDs.");
    mockMvc
        .perform(
            post("/api/v1/order/prompt")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(pr)))
        .andDo(
            result -> log.info("Test call response: {}", result.getResponse().getContentAsString()))
        .andExpect(status().is2xxSuccessful());

    var res =
        orderRepository.findAll().stream()
            .sorted(Comparator.comparing(OrderEntity::getOrderQuantity))
            .toList();

    assertThat(res).isNotNull().hasSize(2);
    assertThat(res.getFirst().getOrderQuantity()).isEqualTo(30);
    assertThat(res.getFirst().getUserID()).isEqualTo("Sophia");
    assertThat(res.getLast().getOrderQuantity()).isEqualTo(40);
    assertThat(res.getLast().getUserID()).isEqualTo("Mary");

    orderRepository.deleteAll();
  }
}
