package com.example.JWT.Token.service.auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    private SecretKey secretKey;

    public JWTService(@Value("${jwt.secretKey}") String initialKeyBeforeHash) {
        this.secretKey = Keys.hmacShaKeyFor(initialKeyBeforeHash.getBytes());
    }

    public String getJWTToken(String email, Map<String,Object> claims){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*10)) // 10 hours
                .signWith(secretKey, Jwts.SIG.HS256)
                .claims(claims)
                .compact();
    }

    public String getSubject(String token){
        Claims data = getTokenData(token);
        return data.getSubject();
    }

    public String getRole (String token){
        Claims data = getTokenData(token);
        if(data != null) {
            return data.get("role").toString();
        }
        return null;
    }

    public Claims getTokenData(String token){
       try{
           return Jwts
                   .parser()
                   .verifyWith(secretKey).build()
                   .parseSignedClaims(token)
                   .getPayload();
       }catch (Exception e){
            e.printStackTrace();
       }
        return null;
    }
}
