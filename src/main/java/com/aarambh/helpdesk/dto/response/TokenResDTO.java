package com.aarambh.helpdesk.dto.response;

public record TokenResDTO(
        String accessToken,
        String refreshToken
) {

}
