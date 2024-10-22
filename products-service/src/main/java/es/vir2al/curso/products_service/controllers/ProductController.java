package es.vir2al.curso.products_service.controllers;

import es.vir2al.curso.products_service.model.dtos.ProductRequest;
import es.vir2al.curso.products_service.model.dtos.ProductResponse;
import es.vir2al.curso.products_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest) {

        this.productService.addProduct(productRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){

        return this.productService.getAllProducts();

    }

}
