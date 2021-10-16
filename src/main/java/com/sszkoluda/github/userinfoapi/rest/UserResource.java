package com.sszkoluda.github.userinfoapi.rest;

import com.sszkoluda.github.userinfoapi.dto.UserDto;
import com.sszkoluda.github.userinfoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getUser(@PathVariable String login) {
        return userService.getUser(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
