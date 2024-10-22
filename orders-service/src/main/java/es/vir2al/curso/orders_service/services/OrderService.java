package es.vir2al.curso.orders_service.services;

import es.vir2al.curso.orders_service.model.dtos.OrderRequest;
import es.vir2al.curso.orders_service.model.dtos.OrderResponse;

import java.util.List;

public interface OrderService {

    public void placeOrder(OrderRequest orderRequest);

    public List<OrderResponse> getAllOrders();

}
