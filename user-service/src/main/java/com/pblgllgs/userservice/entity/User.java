package com.pblgllgs.userservice.entity;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table("users")
public class User {
    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
