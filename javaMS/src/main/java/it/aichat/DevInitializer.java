/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat; /* Italy Company - Fast Team(C) 2024 */

import it.aichat.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevInitializer {
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(DevInitializer.class);

  private final OrderRepository orderRepository;

  public DevInitializer(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PostConstruct
  public void createDummyData() {
    if (orderRepository.count() == 0) {
      log.debug("Created 0 order items");
    }
  }
}
