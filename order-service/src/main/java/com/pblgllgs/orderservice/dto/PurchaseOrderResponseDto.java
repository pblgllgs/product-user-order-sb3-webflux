package com.pblgllgs.orderservice.dto;
/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrderResponseDto {

    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;
}
