package com.scm.contract.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class SecurityService {

    @Value("${jwt.secret_key}")
    String SECRET_KEY;

    @Value("${jwt.expTime}")
    long expTime;

    public String createToken(String subject){
        if(expTime<=0){
            throw new RuntimeException();
        }
        SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
        byte[] secretKeyBytes= DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signatureKey=
                new SecretKeySpec(
                        secretKeyBytes,
                        signatureAlgorithm.getJcaName()
                );
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signatureKey)
                .setExpiration(new Date(System.currentTimeMillis()+expTime))
                .compact();
    }

    public  String getSubject(String tokenBearer){
//        String token=tokenBearer.split(" ")[1].trim();
        String token = tokenBearer.substring("Bearer ".length());
        log.info("token : " + token);
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    /**
     * 헤더에서 토큰 가져오는 메소드
     * @return 유저의 id(pk)
     */
    public String getIdAtToken() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenBearer = request.getHeader("Authorization");
        String id = getSubject(tokenBearer);
        return id;
    }
}
