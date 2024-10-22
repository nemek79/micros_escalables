package es.vir2al.curso.inventory_service.services.impl;

import es.vir2al.curso.inventory_service.model.entities.Inventory;
import es.vir2al.curso.inventory_service.model.entities.dtos.BaseResponse;
import es.vir2al.curso.inventory_service.model.entities.dtos.OrderItemRequest;
import es.vir2al.curso.inventory_service.repositories.InventoryRepository;
import es.vir2al.curso.inventory_service.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;


    @Override
    public boolean isInStock(String sku) {

        var inventory = inventoryRepository.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();

    }

    @Override
    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {

        var errorList = new ArrayList<String>();

        List<String> skus =
                orderItems.stream().map(OrderItemRequest::getSku).toList();

        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream()
                    .filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();

            if (inventory.isEmpty()) {

                errorList.add("Product with sku = ["+orderItem.getSku()+"] does not exists");

            } else if (inventory.get().getQuantity() < orderItem.getQuantity()){

                errorList.add("Product with sku = ["+orderItem.getSku()+"] has insufficient quantity");

            }

        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);

    }
}
