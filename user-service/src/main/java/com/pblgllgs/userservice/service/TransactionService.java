package com.pblgllgs.userservice.service;
/*
 *
 * @author pblgl
 * Created on 12-12-2023
 *
 */

import com.pblgllgs.userservice.dto.TransactionRequestDto;
import com.pblgllgs.userservice.dto.TransactionResponseDto;
import com.pblgllgs.userservice.dto.TransactionStatus;
import com.pblgllgs.userservice.entity.UserTransaction;
import com.pblgllgs.userservice.repository.UserRepository;
import com.pblgllgs.userservice.repository.UserTransactionRepository;
import com.pblgllgs.userservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto transactionRequestDto) {
        return userRepository.updateUserBalance(
                        transactionRequestDto.getUserId(),
                        transactionRequestDto.getAmount()
                )
                .filter(Boolean::booleanValue)
                .map(b -> UtilMapper.toEntity(transactionRequestDto))
                .flatMap(userTransactionRepository::save)
                .map(ut -> UtilMapper.toDto(transactionRequestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(UtilMapper.toDto(transactionRequestDto, TransactionStatus.DECLINE));

    }

    public Flux<UserTransaction> getByUserId(int userId) {
        return userTransactionRepository.findByUserId(userId);
    }
}
