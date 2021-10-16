package com.sszkoluda.github.userinfoapi.mapper;

import com.sszkoluda.github.userinfoapi.domain.UserInfo;
import com.sszkoluda.github.userinfoapi.dto.UserInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserInfoDto userInfoToUserInfoDto(UserInfo userInfo);

    UserInfo userInfoDtoToUserInfo(UserInfoDto userInfoDto);
}
