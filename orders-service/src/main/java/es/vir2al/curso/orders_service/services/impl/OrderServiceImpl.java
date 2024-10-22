package es.vir2al.curso.orders_service.services.impl;

import es.vir2al.curso.orders_service.model.dtos.*;
import es.vir2al.curso.orders_service.model.entities.Order;
import es.vir2al.curso.orders_service.model.entities.OrderItem;
import es.vir2al.curso.orders_service.repositories.OrderRepository;
import es.vir2al.curso.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        // check for inventory
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if (result != null && !result.hasErrors()) {

            Order order = new Order();

            order.setOrderNumber(UUID.randomUUID().toString());

            order.setOrderItems(
                    orderRequest.getOrderItems().stream()
                            .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, order))
                            .toList()
            );

            this.orderRepository.save(order);

        } else {

            throw new IllegalArgumentException("Some products are not in stock");

        }

    }

    @Override
    public List<OrderResponse> getAllOrders() {

       List<Order> orders =  this.orderRepository.findAll();

       return orders.stream().map(this::mapToOrderResponse).toList();

    }

    private OrderResponse mapToOrderResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderItems().stream().map(this::mapToOrderItemResponse).toList()
        );

    }

    private OrderItemResponse mapToOrderItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getId(),
                item.getSku(),
                item.getPrice(),
                item.getQuantity()
        );

    }

    private OrderItem mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order) {

        return OrderItem.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();

    }

}
