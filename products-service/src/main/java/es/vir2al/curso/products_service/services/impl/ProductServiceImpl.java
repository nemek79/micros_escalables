package es.vir2al.curso.products_service.services.impl;

import es.vir2al.curso.products_service.model.dtos.ProductRequest;
import es.vir2al.curso.products_service.model.dtos.ProductResponse;
import es.vir2al.curso.products_service.model.entities.Product;
import es.vir2al.curso.products_service.repositories.ProductRepository;
import es.vir2al.curso.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequest productRequest) {

        var product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product);

        log.info("Product added: {}", product);

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        var products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();

    }
}
