package com.ecommerce.ecommerce.auth.service;



import com.ecommerce.ecommerce.users.model.User;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
//    @Value("${spring.application.security.jwt.expiration}")
//    private long jwtExpiration;
//    @Value("${spring.application.security.jwt.refresh-token.expiration}")
//    private long refreshExpiration;

    public String generateToken(User user) {
        return buildToken(user, new HashMap<>());
    }

//    public String generateRefreshToken(User user) {
//        return buildToken(user, refreshExpiration);
//    }


    private String buildToken(User user, Map<String, Object> claims) {
        return Jwts.builder()
                .id(user.getId())
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +1000*60*24))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getEmailFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

   public boolean isTokenValid(String token, UserDetails userDetails) {
        var email =getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }

   private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
               .verifyWith(getSignKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
   }

   public <T> T getClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
   }

   private Date getExpirationDate(String token) {
        return getClaims(token, Claims::getExpiration);
   }

   private boolean isTokenExpired(String token) {
        return !getExpirationDate(token).before(new Date());
   }
}
