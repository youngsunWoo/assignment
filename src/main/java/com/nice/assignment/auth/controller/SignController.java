package com.nice.assignment.auth.controller;


import com.nice.assignment.auth.entity.User;
import com.nice.assignment.auth.exception.UserRuntimeException;
import com.nice.assignment.auth.repository.UserJpaRepository;
import com.nice.assignment.auth.responce.ApiResponse;
import com.nice.assignment.auth.responce.ApiResponseCode;
import com.nice.assignment.config.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Tag(name = "Authentication API", description = "권한 관련 API(회원가입/로그인/토큰갱신)")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "로그인", description = "회원 로그인 API")
    @PostMapping(value = "/signin")
    public ApiResponse<String> signin(@RequestParam String id,
                                       @RequestParam String password) {
        User user = userJpaRepository.findByUid(id).orElseThrow(
                () -> new UserRuntimeException(ApiResponseCode.BAD_REQUEST,"User Id is not Exist"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new UserRuntimeException(ApiResponseCode.BAD_REQUEST,"Incorrect Password");

        return ApiResponse.of(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @Operation(summary = "가입", description = "회원가입 API")
    @PostMapping(value = "/signup")
    public ApiResponse signup(@RequestParam String id,
                              @RequestParam String password,
                              @RequestParam String name) {
        userJpaRepository.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build());
        return ApiResponse.SUCCESS;
    }

    @Operation(summary = "토큰 갱신", description = "토큰 갱신 API")
    @Parameter(in = ParameterIn.HEADER, name = "X-AUTH-TOKEN", description = "API 인증토큰")
    @PostMapping(value = "/token")
    public ApiResponse signup() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return ApiResponse.of(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }
}