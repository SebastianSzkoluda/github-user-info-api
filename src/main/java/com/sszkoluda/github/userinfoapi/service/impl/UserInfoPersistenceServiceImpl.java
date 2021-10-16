package com.sszkoluda.github.userinfoapi.service.impl;

import com.sszkoluda.github.userinfoapi.dto.UserInfoDto;
import com.sszkoluda.github.userinfoapi.mapper.UserInfoMapper;
import com.sszkoluda.github.userinfoapi.repository.UserInfoRepository;
import com.sszkoluda.github.userinfoapi.service.UserInfoPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoPersistenceServiceImpl implements UserInfoPersistenceService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDto save(UserInfoDto userInfoDto) {
        var userInfo = userInfoMapper.userInfoDtoToUserInfo(userInfoDto);
        return userInfoMapper.userInfoToUserInfoDto(userInfoRepository.save(userInfo));
    }

    @Override
    public Optional<UserInfoDto> getUserInfo(String login) {
        return userInfoRepository.findByLogin(login)
                .map(userInfoMapper::userInfoToUserInfoDto);
    }
}
