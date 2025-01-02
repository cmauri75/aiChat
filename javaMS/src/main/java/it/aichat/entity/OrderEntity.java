/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class OrderEntity {
  @Id private UUID orderID;

  private String userID;

  private Integer orderQuantity;

  public OrderEntity() {
    super();
  }

  public OrderEntity(Integer orderQuantity, String userID) {
    this.orderQuantity = orderQuantity;
    this.userID = userID;
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
