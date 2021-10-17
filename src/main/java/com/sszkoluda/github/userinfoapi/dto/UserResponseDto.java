package com.sszkoluda.github.userinfoapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String login;

    private String name;

    private String type;

    private String avatar_url;

    private Instant created_at;

    private Integer followers;

    private Integer public_repos;
}
