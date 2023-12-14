package com.pblgllgs.orderservice.service;
/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */

import com.pblgllgs.orderservice.dto.PurchaseOrderResponseDto;
import com.pblgllgs.orderservice.repository.PurchaseOrderRepository;
import com.pblgllgs.orderservice.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public Flux<PurchaseOrderResponseDto> productByUserId(int userId) {

        return Flux.fromStream(
                        () -> purchaseOrderRepository.findByUserId(userId).stream())
                .map(EntityUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
