package com.example.jwt;

import com.example.sercurity.CusTomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${ra.jwt.secret}")
    private String JWT_SECRET;
    @Value(("${ra.jwt.expiration}"))
    private int JWT_EXPIRATION;
    // tạo jwt tu thong tin của uesr

    public String generateToken(CusTomUserDetail cusTomUserDetail){
        Date now = new Date();
        Date dateExpired = new Date(now.getTime()+JWT_EXPIRATION);
        // tạo chuỗi jwt bằng userId
        return Jwts.builder()
                .setSubject(cusTomUserDetail.getUsername().toString())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }

    // lay thong tin user

    public String getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken){
           try {
               Jwts.parser().setSigningKey(JWT_SECRET)
                       .parseClaimsJws(authToken);
               return true;
           }catch (Exception e){
               e.printStackTrace();
               return false;
           }
    }
}
