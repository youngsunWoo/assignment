package com.nice.assignment.auth.controller;


import com.nice.assignment.auth.dto.LoginDto;
import com.nice.assignment.auth.dto.UserDto;
import com.nice.assignment.auth.entity.User;
import com.nice.assignment.common.exception.CustomRuntimeException;
import com.nice.assignment.auth.repository.UserJpaRepository;
import com.nice.assignment.common.response.ApiResponse;
import com.nice.assignment.common.response.ApiResponseCode;
import com.nice.assignment.config.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/sign-in")
    public ApiResponse<String> signin(@RequestBody LoginDto loginDto) {
        User user = userJpaRepository.findByUid(loginDto.getId()).orElseThrow(
                () -> new CustomRuntimeException(ApiResponseCode.BAD_REQUEST,"User Id is not Exist"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
            throw new CustomRuntimeException(ApiResponseCode.BAD_REQUEST,"Incorrect Password");

        return ApiResponse.of(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @Operation(summary = "가입", description = "회원가입 API")
    @PostMapping(value = "/sign-up")
    public ApiResponse signup(@RequestBody UserDto userDto) {
        userJpaRepository.save(User.builder()
                .uid(userDto.getId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build());
        return ApiResponse.SUCCESS;
    }

    @Operation(summary = "토큰 갱신", description = "토큰 갱신 API")
    @Parameter(in = ParameterIn.HEADER, name = "X-AUTH-TOKEN", description = "API 인증토큰")
    @PostMapping(value = "/token-refresh")
    public ApiResponse signup() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return ApiResponse.of(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }
}