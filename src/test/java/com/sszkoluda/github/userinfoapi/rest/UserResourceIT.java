package com.sszkoluda.github.userinfoapi.rest;

import com.sszkoluda.github.userinfoapi.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    void shouldReturn404WhenUserNotFound() throws Exception {
        //given
        int numberOfObjectsBefore = userInfoRepository.findAll().size();

        //when
        mockMvc.perform(get("/users/notExistingLogin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        //then
        int numberOfObjectsAfter = userInfoRepository.findAll().size();
        assertThat(numberOfObjectsAfter).isEqualTo(numberOfObjectsBefore);
    }

    @Test
    void shouldReturnUser() throws Exception {
        //given
        int numberOfObjectsBefore = userInfoRepository.findAll().size();

        //when
        mockMvc.perform(get("/users/octocat")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        int numberOfObjectsAfter = userInfoRepository.findAll().size();
        assertThat(numberOfObjectsAfter).isEqualTo(numberOfObjectsBefore + 1);
    }
}