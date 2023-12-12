package com.pblgllgs.productservice.service;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.productservice.dto.ProductDto;
import com.pblgllgs.productservice.repository.ProductRepository;
import com.pblgllgs.productservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<ProductDto> findAllProducts() {
        return productRepository.findAll().map(UtilMapper::toDto);
    }

    public Mono<ProductDto> findProductById(String id) {
        return productRepository.findById(id).map(UtilMapper::toDto);
    }

    public Flux<ProductDto> findProductsByPriceBetween(Integer min, Integer max) {
        return productRepository
                .findProductsByPriceBetween(min,max)
                .map(UtilMapper::toDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(UtilMapper::toEntity)
                .flatMap(productRepository::insert)
                .map(UtilMapper::toDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
        return productRepository
                .findById(id)
                .flatMap(
                        p -> productDtoMono
                                .map(UtilMapper::toEntity)
                                .doOnNext(e -> e.setId(id)
                                )
                )
                .flatMap(productRepository::save)
                .map(UtilMapper::toDto);

    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
