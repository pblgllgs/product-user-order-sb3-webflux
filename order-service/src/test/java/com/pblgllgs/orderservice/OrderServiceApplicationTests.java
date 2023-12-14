package com.pblgllgs.orderservice;

import com.pblgllgs.orderservice.client.ProductClient;
import com.pblgllgs.orderservice.client.UserClient;
import com.pblgllgs.orderservice.dto.ProductDto;
import com.pblgllgs.orderservice.dto.PurchaseOrderRequestDto;
import com.pblgllgs.orderservice.dto.PurchaseOrderResponseDto;
import com.pblgllgs.orderservice.dto.UserDto;
import com.pblgllgs.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderFulfillmentService orderFulfillmentService;

    @Test
    void contextLoads() {
        Flux<PurchaseOrderResponseDto> dtoFlux = Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
                .map(t -> buildDto(t.getT1(), t.getT2()))
                .flatMap(dto -> orderFulfillmentService.processOrder(Mono.just(dto)))
                .doOnNext(System.out::println);
        StepVerifier.create(dtoFlux)
                .expectNextCount(3)
                .verifyComplete();
    }

    private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto){
        PurchaseOrderRequestDto purchaseOrderRequestDto =  new PurchaseOrderRequestDto();
        purchaseOrderRequestDto.setUserId(userDto.getId());
        purchaseOrderRequestDto.setProductId(productDto.getId());
        return purchaseOrderRequestDto;
    }

}
