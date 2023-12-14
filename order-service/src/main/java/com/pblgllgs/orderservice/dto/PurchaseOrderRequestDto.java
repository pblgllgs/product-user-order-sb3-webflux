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
public class PurchaseOrderRequestDto {

    private Integer userId;
    private String productId;
}
