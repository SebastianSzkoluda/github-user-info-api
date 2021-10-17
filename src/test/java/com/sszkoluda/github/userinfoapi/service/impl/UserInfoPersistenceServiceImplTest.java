package com.sszkoluda.github.userinfoapi.service.impl;

import com.sszkoluda.github.userinfoapi.domain.UserInfo;
import com.sszkoluda.github.userinfoapi.dto.UserInfoDto;
import com.sszkoluda.github.userinfoapi.mapper.UserInfoMapper;
import com.sszkoluda.github.userinfoapi.repository.UserInfoRepository;
import com.sszkoluda.github.userinfoapi.service.UserInfoPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserInfoPersistenceServiceImplTest {

    private static final String LOGIN = "login";
    private static final long REQUEST_COUNT = 1L;

    private UserInfoPersistenceService userInfoPersistenceService;

    @Mock
    private UserInfoRepository userInfoRepository;

    private UserInfoMapper userInfoMapper;

    @Captor
    private ArgumentCaptor<UserInfo> userInfoCaptor;

    @BeforeEach
    void init() {
        this.userInfoMapper = Mappers.getMapper(UserInfoMapper.class);
        this.userInfoPersistenceService = new UserInfoPersistenceServiceImpl(userInfoRepository, userInfoMapper);
    }

    @Test
    void shouldCreateUserInfo() {
        //given
        var userInfoDto = mockUserInfoDto();
        var userInfo = userInfoMapper.userInfoDtoToUserInfo(userInfoDto);

        //when
        userInfoPersistenceService.save(userInfoDto);

        //then
        verify(userInfoRepository).save(userInfoCaptor.capture());
        UserInfo result = userInfoCaptor.getValue();
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(userInfo);
    }

    @Test
    void shouldGetUserInfoByLogin() {
        //when
        userInfoPersistenceService.getUserInfo(LOGIN);

        //then
        verify(userInfoRepository).findByLogin(LOGIN);
    }

    private UserInfoDto mockUserInfoDto() {
        return UserInfoDto.builder()
                .login(LOGIN)
                .requestCount(REQUEST_COUNT)
                .build();
    }
}