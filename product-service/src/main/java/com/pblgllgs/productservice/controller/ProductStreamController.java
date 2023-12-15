package com.pblgllgs.productservice.controller;

import com.pblgllgs.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/*
 *
 * @author pblgl
 * Created on 15-12-2023
 *
 */
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductStreamController {

    private final Flux<ProductDto> flux;

    @GetMapping(value = "/stream/{maxPrice}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductDto> getProductUpdates(@PathVariable("maxPrice") int maxPrice){
        return flux
                .filter(dto -> dto.getPrice() <= maxPrice);
    }
}
