package es.vir2al.curso.products_service.services;

import es.vir2al.curso.products_service.model.dtos.ProductRequest;
import es.vir2al.curso.products_service.model.dtos.ProductResponse;

import java.util.List;

public interface ProductService {

    public void addProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();

}
