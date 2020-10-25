package com.nice.assignment.auth.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String id;
    private String password;
}
