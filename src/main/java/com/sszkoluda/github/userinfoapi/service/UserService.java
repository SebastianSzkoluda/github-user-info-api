package com.sszkoluda.github.userinfoapi.service;

import com.sszkoluda.github.userinfoapi.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getUser(String login);
}
