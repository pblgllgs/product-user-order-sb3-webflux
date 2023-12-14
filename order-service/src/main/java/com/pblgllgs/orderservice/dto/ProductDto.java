package com.pblgllgs.orderservice.dto;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String id;
    private String description;
    private Integer price;
}
