package com.license.studentscenespring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "_user_token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    private Date expirationTime;
    private boolean used;
    private static final int EXPIRATION_TIME = 60;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public Token(String token, User user) {
        super();
        this.token = token;
        this.user = user;
        this.used = false;
        this.expirationTime = this.getExpirationTime();
    }

    public Date getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }

    public boolean isTokenExpired() {
        Date now = new Date();
        return !now.after(this.expirationTime);
    }
}
