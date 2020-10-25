package com.nice.assignment.auth.service;

import com.nice.assignment.auth.exception.UserRuntimeException;
import com.nice.assignment.auth.repository.UserJpaRepository;
import com.nice.assignment.common.response.ApiResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepository.findById(Long.valueOf(userPk)).orElseThrow(
                () -> new UserRuntimeException(ApiResponseCode.BAD_REQUEST,"User Id is not Exist")
        );
    }
}