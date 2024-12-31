package it.aichat.repository;

import java.util.List;
import java.util.Optional;

import it.aichat.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Override
    OrderEntity save(OrderEntity entity);
    Optional<List<OrderEntity>> findByUserID(String userID);
}
