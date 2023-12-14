package com.pblgllgs.orderservice.dto;

import lombok.*;

/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDto {
    private Integer userId;
    private Integer amount;
    private TransactionStatus status;
}
