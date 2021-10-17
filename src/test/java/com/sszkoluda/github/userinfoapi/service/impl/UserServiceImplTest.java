package com.sszkoluda.github.userinfoapi.service.impl;

import com.sszkoluda.github.userinfoapi.dto.UserDto;
import com.sszkoluda.github.userinfoapi.dto.UserResponseDto;
import com.sszkoluda.github.userinfoapi.service.UserInfoPersistenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final String LOGIN = "login";
    private static final String NAME = "Name";
    private static final long ID = 1L;
    private static final int FOLLOWERS = 8;
    private static final int PUBLIC_REPOS = 2;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private UserInfoPersistenceService userInfoPersistenceService;

    @Test
    void shouldReturnOptionalEmptyWhenUserNotFound() {
        //given
        given(restTemplate.getForObject(anyString(), eq(UserResponseDto.class)))
                .willReturn(null);

        //when
        Optional<UserDto> maybeUser = userService.getUser(LOGIN);

        //then
        assertThat(maybeUser).isEmpty();
    }

    @Test
    void shouldReturnOptionalWithValueWhenUserFound() {
        //given
        var userResponseDto = mockUserResponseDto();
        var expectedUserDto = mockUserDto();
        given(restTemplate.getForObject(anyString(), eq(UserResponseDto.class)))
                .willReturn(userResponseDto);

        //when
        Optional<UserDto> maybeUser = userService.getUser(LOGIN);

        //then
        assertThat(maybeUser)
                .get()
                .isEqualTo(expectedUserDto);
    }

    @Test
    void shouldReturnUserWithCalculations() {
        //given
        var userResponseDto = mockUserResponseDtoWithAdditionalData();
        var expectedUserDto = mockUserDtoWithCalculations();
        given(restTemplate.getForObject(anyString(), eq(UserResponseDto.class)))
                .willReturn(userResponseDto);

        //when
        Optional<UserDto> maybeUser = userService.getUser(LOGIN);

        //then
        assertThat(maybeUser)
                .get()
                .isEqualTo(expectedUserDto);
    }

    private UserResponseDto mockUserResponseDto() {
        var userResponseDto = new UserResponseDto();
        userResponseDto.setId(ID);
        userResponseDto.setName(NAME);
        userResponseDto.setLogin(LOGIN);
        return userResponseDto;
    }

    private UserResponseDto mockUserResponseDtoWithAdditionalData() {
        var userResponseDto = new UserResponseDto();
        userResponseDto.setId(ID);
        userResponseDto.setName(NAME);
        userResponseDto.setLogin(LOGIN);
        userResponseDto.setFollowers(FOLLOWERS);
        userResponseDto.setPublic_repos(PUBLIC_REPOS);
        return userResponseDto;
    }

    private UserDto mockUserDto() {
        return UserDto.builder()
                .id(ID)
                .name(NAME)
                .login(LOGIN)
                .calculations(0.0)
                .build();
    }

    private UserDto mockUserDtoWithCalculations() {
        return UserDto.builder()
                .id(ID)
                .name(NAME)
                .login(LOGIN)
                .calculations(0.1875)
                .build();
    }
}