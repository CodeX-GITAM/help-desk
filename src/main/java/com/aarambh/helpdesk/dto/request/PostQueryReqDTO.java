package com.aarambh.helpdesk.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PostQueryReqDTO(
        @NotEmpty(message = "Query is required")
        String query

) {
}
