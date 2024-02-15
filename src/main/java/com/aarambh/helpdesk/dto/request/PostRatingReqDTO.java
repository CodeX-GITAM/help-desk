package com.aarambh.helpdesk.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PostRatingReqDTO(
        @NotEmpty(message = "Query id is required")
        int rating,
        @NotEmpty(message = "Rating by is required")
        String ratingBy,

        @NotEmpty(message = "Commentz is required")
        String comment
) {
}
