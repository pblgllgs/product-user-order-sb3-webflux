package com.pblgllgs.productservice.entity;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Product {

    @Id
    private String id;
    private String description;
    private Integer price;
}
