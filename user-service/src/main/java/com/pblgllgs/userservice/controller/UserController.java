package com.pblgllgs.userservice.controller;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.userservice.dto.UserDto;
import com.pblgllgs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Flux<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<UserDto>> findUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<UserDto> saveUser(@RequestBody Mono<UserDto> userDtoMono) {
        return userService.saveUser(userDtoMono);
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<UserDto>> updateUser(
            @PathVariable("userId") int userId,
            @RequestBody Mono<UserDto> userDtoMono
    ) {
        return userService.updateUser(userId, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(@PathVariable("userId") int id) {
        return userService.deleteUser(id);
    }
}
