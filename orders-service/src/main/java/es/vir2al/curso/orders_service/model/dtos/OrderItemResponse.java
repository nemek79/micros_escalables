package es.vir2al.curso.orders_service.model.dtos;

public record OrderItemResponse(Long id, String sku, Double price, long quantity) {
}
