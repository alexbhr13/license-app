package com.license.studentscenespring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final UserDetailsService userDetailsService;

    private static final long EXPIRATION_TIME = Duration.ofDays(3).toMillis();
    @Value("${secret}")
    private String secret;


    public Authentication getAuthentication(String token) throws Exception {
        String userEmail = extractEmail(token);
        boolean isAdmin = extractIsAdmin(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        if(isTokenValid(token,userDetails)) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            if(isAdmin) {
                authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(userDetails,null,authorityList);
        } else {
            throw new Exception("Invalid token");
        }
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean extractIsAdmin(String token) {
        return extractClaim(token, claims -> claims.get("isAdmin", Boolean.class));
    }

    public Long getUserIDFromToken(String token) {
        return extractClaim(token, claims -> claims.get("id", Long.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public String generateToken(String email, boolean isAdmin) {
        return Jwts.builder()
                .header()
                    .add("alg","HS512")
                    .add("typ","JWT")
                .and()
                .subject(email)
                .claim("isAdmin",isAdmin)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, "HmacSHA512");
    }


}
