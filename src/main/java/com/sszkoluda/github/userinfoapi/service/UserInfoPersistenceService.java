package com.sszkoluda.github.userinfoapi.service;

import com.sszkoluda.github.userinfoapi.dto.UserInfoDto;

import java.util.Optional;

public interface UserInfoPersistenceService {
    UserInfoDto save(UserInfoDto userInfoDto);

    Optional<UserInfoDto> getUserInfo(String login);
}
