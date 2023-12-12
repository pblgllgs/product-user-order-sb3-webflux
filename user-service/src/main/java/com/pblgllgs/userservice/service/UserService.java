package com.pblgllgs.userservice.service;
/*
 *
 * @author pblgl
 * Created on 11-12-2023
 *
 */

import com.pblgllgs.userservice.dto.UserDto;
import com.pblgllgs.userservice.repository.UserRepository;
import com.pblgllgs.userservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<UserDto> findAllUsers() {
        return userRepository.findAll().map(UtilMapper::toDto);
    }

    public Mono<UserDto> findUserById(final int userId){
        return userRepository.findById(userId)
                .map(UtilMapper::toDto);
    }

    public Mono<UserDto> saveUser(Mono<UserDto> userDto){
        return userDto
                .map(UtilMapper::toEntity)
                .flatMap(userRepository::save)
                .map(UtilMapper::toDto);

    }

    public Mono<UserDto> updateUser(Integer userId,Mono<UserDto> userDtoMono){
        return userRepository
                .findById(userId)
                .flatMap(
                        p -> userDtoMono
                                .map(UtilMapper::toEntity)
                                .doOnNext(e -> e.setId(userId)
                                )
                )
                .flatMap(userRepository::save)
                .map(UtilMapper::toDto);

    }

    public Mono<Void> deleteUser(Integer userId){
        return userRepository.deleteById(userId);
    }
}
