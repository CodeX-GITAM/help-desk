package com.aarambh.helpdesk.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record TeamReqDTO(
        @NotEmpty(message = "Desk number is required")
        int deskNumber,
        @NotEmpty(message = "Team name is required")
        String teamName
) {
}
