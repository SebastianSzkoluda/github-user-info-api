package com.sszkoluda.github.userinfoapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

    private Long id;

    private String login;

    private Long requestCount;
}
