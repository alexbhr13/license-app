package com.license.studentscenespring.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate academicYearStartDate;
    private int duration;
    private String photoURL;
    private String universityName;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private User user;

}
