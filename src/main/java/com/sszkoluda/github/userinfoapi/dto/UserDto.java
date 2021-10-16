package com.sszkoluda.github.userinfoapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserDto {

    private Long id;

    private String login;

    private String name;

    private String type;

    private String avatarUrl;

    private Instant createdAt;

    private Double calculations;
}
