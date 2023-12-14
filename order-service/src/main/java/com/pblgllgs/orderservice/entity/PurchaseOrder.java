package com.pblgllgs.orderservice.entity;

import com.pblgllgs.orderservice.dto.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */
@Data
@ToString
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private String productId;
    private Integer userId;
    private Integer amount;
    private OrderStatus status;
}
