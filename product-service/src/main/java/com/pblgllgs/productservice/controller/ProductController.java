package com.pblgllgs.productservice.controller;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.productservice.dto.ProductDto;
import com.pblgllgs.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<ProductDto>> findProductById(@PathVariable("productId") String id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productMono) {
        return productService.saveProduct(productMono);
    }

    @PutMapping("/{productId}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(
            @PathVariable("productId") String productId,
            @RequestBody Mono<ProductDto> productDtoMono
    ) {
        return productService.updateProduct(productId, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public Mono<Void> deleteProduct(@PathVariable("productId") String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/price-range")
    public Flux<ProductDto> findProductsByPriceBetween(
            @RequestParam("min") Integer min,
            @RequestParam("max") Integer max
    ) {
        return productService.findProductsByPriceBetween(min, max);
    }

}
