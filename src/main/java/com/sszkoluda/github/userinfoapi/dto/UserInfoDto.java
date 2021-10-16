package com.sszkoluda.github.userinfoapi.dto;

import lombok.*;

@Data
@Builder
public class UserInfoDto {

    private Long id;

    private String login;

    private Long requestCount;
}
