package com.pblgllgs.userservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */
@Data
@ToString
public class UserTransaction {

    @Id
    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime transactionDate;
}
