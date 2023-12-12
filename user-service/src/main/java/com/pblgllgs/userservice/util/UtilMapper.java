package com.pblgllgs.userservice.util;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.userservice.dto.TransactionRequestDto;
import com.pblgllgs.userservice.dto.TransactionResponseDto;
import com.pblgllgs.userservice.dto.TransactionStatus;
import com.pblgllgs.userservice.dto.UserDto;
import com.pblgllgs.userservice.entity.User;
import com.pblgllgs.userservice.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class UtilMapper {

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto dto){
        UserTransaction userTransaction =  new UserTransaction();
        userTransaction.setUserId(dto.getUserId());
        userTransaction.setAmount(dto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto transactionRequestDto, TransactionStatus status){
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        transactionResponseDto.setUserId(transactionRequestDto.getUserId());
        transactionResponseDto.setAmount(transactionRequestDto.getAmount());
        transactionResponseDto.setStatus(status);
        return transactionResponseDto;
    }
}
