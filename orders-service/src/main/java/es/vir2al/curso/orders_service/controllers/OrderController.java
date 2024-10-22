package es.vir2al.curso.orders_service.controllers;

import es.vir2al.curso.orders_service.model.dtos.OrderRequest;
import es.vir2al.curso.orders_service.model.dtos.OrderResponse;
import es.vir2al.curso.orders_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        this.orderService.placeOrder(orderRequest);

        return "Order placed successfully";

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrder() {

        return this.orderService.getAllOrders();

    }

}
