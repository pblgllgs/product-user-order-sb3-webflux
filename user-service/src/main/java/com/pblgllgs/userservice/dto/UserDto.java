package com.pblgllgs.userservice.dto;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private Integer id;
    private String name;
    private Integer balance;
}
