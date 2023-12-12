package com.pblgllgs.userservice.repository;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    @Modifying
    @Query("update users set balance = balance - :amount where id = :userId and balance >= :amount")
    Mono<Boolean> updateUserBalance(int userId, int amount);
}
