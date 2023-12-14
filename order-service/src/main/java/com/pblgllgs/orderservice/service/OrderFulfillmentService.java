package com.pblgllgs.orderservice.service;
/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */

import com.pblgllgs.orderservice.client.ProductClient;
import com.pblgllgs.orderservice.client.UserClient;
import com.pblgllgs.orderservice.dto.PurchaseOrderRequestDto;
import com.pblgllgs.orderservice.dto.PurchaseOrderResponseDto;
import com.pblgllgs.orderservice.dto.RequestContext;
import com.pblgllgs.orderservice.entity.PurchaseOrder;
import com.pblgllgs.orderservice.repository.PurchaseOrderRepository;
import com.pblgllgs.orderservice.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final ProductClient productClient;
    private final UserClient userClient;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> purchaseOrderRequestDtoMono){
        return purchaseOrderRequestDtoMono
                .map(RequestContext::new)
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .map(EntityUtil::getPurchaseOrder)
                .map(purchaseOrderRepository::save)
                .map(EntityUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<RequestContext> productRequestResponse(RequestContext requestContext){
        return productClient.getProductById(requestContext.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(requestContext::setProductDto)
                .thenReturn(requestContext);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext requestContext){
        return userClient.authorizeTransaction(requestContext.getTransactionRequestDto())
                .doOnNext(requestContext::setTransactionResponseDto)
                .thenReturn(requestContext);
    }

    public List<PurchaseOrder> findByUserId(int userId) {
        return purchaseOrderRepository.findByUserId(userId);
    }
}
