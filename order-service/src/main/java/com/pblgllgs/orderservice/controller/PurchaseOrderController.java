package com.pblgllgs.orderservice.controller;
/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */

import com.pblgllgs.orderservice.dto.PurchaseOrderRequestDto;
import com.pblgllgs.orderservice.dto.PurchaseOrderResponseDto;
import com.pblgllgs.orderservice.service.OrderFulfillmentService;
import com.pblgllgs.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final OrderFulfillmentService orderFulfillmentService;

    private final OrderQueryService orderQueryService;

    @PostMapping
    public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(@RequestBody Mono<PurchaseOrderRequestDto> purchaseOrderRequestDtoMono) {
        return orderFulfillmentService.processOrder(purchaseOrderRequestDtoMono)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());

    }

    @GetMapping("/user/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserId(@PathVariable("userId") int userId) {
        return orderQueryService.productByUserId(userId);
    }
}
