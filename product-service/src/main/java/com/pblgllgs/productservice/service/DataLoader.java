package com.pblgllgs.productservice.service;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.github.javafaker.Faker;
import com.pblgllgs.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        List<ProductDto> products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Faker faker = new Faker();
            ProductDto productDto = ProductDto.builder()
                    .price(faker.number().numberBetween(1, 99999))
                    .description(faker.lorem().sentence())
                    .build();
            products.add(productDto);
        }

        Flux.fromIterable(products)
                .flatMap(p -> productService.saveProduct(Mono.just(p)))
                .subscribe(System.out::println);
    }
}
