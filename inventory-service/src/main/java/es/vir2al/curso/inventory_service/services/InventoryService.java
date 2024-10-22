package es.vir2al.curso.inventory_service.services;

import es.vir2al.curso.inventory_service.model.entities.dtos.BaseResponse;
import es.vir2al.curso.inventory_service.model.entities.dtos.OrderItemRequest;

import java.util.List;

public interface InventoryService {

    public boolean isInStock(String sku);

    public BaseResponse areInStock(List<OrderItemRequest> orderItems);

}
