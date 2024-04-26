package com.license.studentscenespring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponseDTO {
    private String email;
    private Boolean isAdmin;
    private Boolean isConfirmed;
}
