package com.license.studentscenespring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class RegistrationDTO {

    private String email;
    private String password;
    @JsonProperty("isAdmin")
    private boolean is_admin;
}
