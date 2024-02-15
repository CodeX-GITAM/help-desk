package com.aarambh.helpdesk.dto.response;

public record ResponseDTO<D>(
        D data,
        BasicResDTO basicResDTO
) {

}
