package com.aarambh.helpdesk.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserSigninReqDTO(

        @NotEmpty
        String username,
        @NotEmpty
        String password
) {
}
