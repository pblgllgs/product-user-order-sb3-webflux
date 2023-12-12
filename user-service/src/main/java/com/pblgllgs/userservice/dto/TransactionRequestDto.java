package com.pblgllgs.userservice.dto;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import lombok.*;

@Data
@ToString
public class TransactionRequestDto {

    private Integer userId;
    private Integer amount;
}
