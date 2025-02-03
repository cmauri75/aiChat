/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class OrderEntity {
  @Id private UUID orderID;

  private String userID;

  private Integer orderQuantity;

  public OrderEntity() {
    super();
  }

  public OrderEntity(UUID orderID, Integer orderQuantity, String userID) {
    this.orderID = orderID;
    this.orderQuantity = orderQuantity;
    this.userID = userID;
  }

  public OrderEntity(String json) {
    try {
      OrderEntity data = new ObjectMapper().readValue(json, OrderEntity.class);
      this.orderID = data.orderID;
      this.orderQuantity = data.orderQuantity;
      this.userID = data.userID;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public UUID getOrderID() {
    return orderID;
  }

  public void setOrderID(UUID orderID) {
    this.orderID = orderID;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public Integer getOrderQuantity() {
    return orderQuantity;
  }

  public void setOrderQuantity(Integer orderQuantity) {
    this.orderQuantity = orderQuantity;
  }

  @Override
  public String toString() {
    return "OrderEntity{"
        + "orderID="
        + orderID
        + ", userID='"
        + userID
        + '\''
        + ", orderQuantity="
        + orderQuantity
        + '}';
  }
}
