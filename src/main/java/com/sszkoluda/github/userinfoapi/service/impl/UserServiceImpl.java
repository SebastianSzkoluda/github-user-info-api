package com.sszkoluda.github.userinfoapi.service.impl;

import com.sszkoluda.github.userinfoapi.dto.UserDto;
import com.sszkoluda.github.userinfoapi.dto.UserInfoDto;
import com.sszkoluda.github.userinfoapi.dto.UserResponseDto;
import com.sszkoluda.github.userinfoapi.service.UserInfoPersistenceService;
import com.sszkoluda.github.userinfoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${github.api.users.path}")
    private String githubApiPath;

    private final UserInfoPersistenceService userInfoPersistenceService;
    private final RestTemplate restTemplate;

    @Override
    public Optional<UserDto> getUser(String login) {
        var userResponseDto = restTemplate.getForObject(githubApiPath + "/" + login, UserResponseDto.class);
        var maybeUserResponse = Optional.ofNullable(userResponseDto);
        var maybeUserDto = maybeUserResponse.map(this::mapToUserDto);
        maybeUserDto.ifPresent(userDto -> createOrUpdateUserInfoDto(userDto.getLogin()));
        return maybeUserDto;
    }

    private UserDto mapToUserDto(UserResponseDto userResponseDto) {
        return UserDto.builder()
                .id(userResponseDto.getId())
                .login(userResponseDto.getLogin())
                .name(userResponseDto.getName())
                .type(userResponseDto.getType())
                .avatarUrl(userResponseDto.getAvatar_url())
                .createdAt(userResponseDto.getCreated_at())
                .calculations(calculate(userResponseDto))
                .build();
    }

    private Double calculate(UserResponseDto userResponseDto) {
        if (userResponseDto.getFollowers() == null || userResponseDto.getFollowers() == 0
                || userResponseDto.getPublic_repos() == null) {
            return 0.0;
        }
        return 6 / ((double) userResponseDto.getFollowers() * (2 + userResponseDto.getPublic_repos()));
    }

    private UserInfoDto createOrUpdateUserInfoDto(String login) {
        var userInfoDto = userInfoPersistenceService.getUserInfo(login)
                .map(this::updateUserInfo)
                .orElseGet(() -> createUserInfoDto(login));

        return userInfoPersistenceService.save(userInfoDto);
    }

    private UserInfoDto createUserInfoDto(String login) {
        return UserInfoDto.builder()
                .login(login)
                .requestCount(1L)
                .build();
    }

    private UserInfoDto updateUserInfo(UserInfoDto userInfoDto) {
        return UserInfoDto.builder()
                .id(userInfoDto.getId())
                .login(userInfoDto.getLogin())
                .requestCount(userInfoDto.getRequestCount() + 1)
                .build();
    }
}
