package es.vir2al.curso.products_service.repositories;

import es.vir2al.curso.products_service.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {



}
