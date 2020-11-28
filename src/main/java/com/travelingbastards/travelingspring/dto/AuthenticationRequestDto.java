package com.travelingbastards.travelingspring.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String nickName;
    private String password;
}
