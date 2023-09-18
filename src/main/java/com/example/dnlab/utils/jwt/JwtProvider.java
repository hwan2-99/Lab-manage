package com.example.dnlab.utils.jwt;

import com.example.dnlab.domain.auth.PrincipalDetails;
import com.example.dnlab.service.auth.PrincipalDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class JwtProvider {
    private final PrincipalDetailsService principalDetailsService;

    @Value("${jwt.access-token.expire-length}")
    private long accessTokenExpiredIn;

    @Value("${jwt.refresh-token.expire-length}")
    private long refreshTokenExpiredIn;

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    public Token createToken(String payload) {
        return Token.builder()
                .accessToken(createAccessToken(payload))
                .refreshToken(createRefreshToken())
                .build();
    }

    public String createAccessToken(String payload) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpiredIn);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);

        Claims claims = Jwts.claims().setSubject(randomString);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpiredIn);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public Authentication getAuthentication(String token) {
        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(getPayload(token));
        log.info("getAuthentication, email={}", principalDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
    }
    public String getPayload(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
