package com.license.studentscenespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate academicYearStartDate;
    private int duration;
    private String photoURL;
    private String universityName;
    private Long userId;
}
