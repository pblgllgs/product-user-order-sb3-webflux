package com.pblgllgs.userservice.controller;
/*
 *
 * @author pblgl
 * Created on 12-12-2023
 *
 */

import com.pblgllgs.userservice.dto.TransactionRequestDto;
import com.pblgllgs.userservice.dto.TransactionResponseDto;
import com.pblgllgs.userservice.entity.UserTransaction;
import com.pblgllgs.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/transaction")
public class UserTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> transactionRequestDtoMono){
        return transactionRequestDtoMono.flatMap(transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId){
        return transactionService.getByUserId(userId);
    }
}
