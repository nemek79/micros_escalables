package es.vir2al.curso.orders_service.repositories;

import es.vir2al.curso.orders_service.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
