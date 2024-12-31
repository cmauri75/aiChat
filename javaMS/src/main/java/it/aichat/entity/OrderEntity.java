package it.aichat.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderID;

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
}
