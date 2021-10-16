package com.sszkoluda.github.userinfoapi.repository;

import com.sszkoluda.github.userinfoapi.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByLogin(String login);
}
