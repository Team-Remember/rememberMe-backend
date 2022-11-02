package com.yjh.rememberme.auth.util;

import com.yjh.rememberme.database.entity.LoginLog;
import com.yjh.rememberme.database.entity.Member;
import com.yjh.rememberme.database.repository.LoginLogRepository;
import com.yjh.rememberme.database.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend/blob/main/src/main/java/com/steady/leisurethatapi/auth/util/JwtUtil.java
 */

@Service
public class JwtUtil {
    @Value("remembermerememberyou")
    private String secret;
    @Value("43200")
    private Integer expireSecond;
    private final LoginLogRepository loginLogRepository;
    private final MemberRepository memberRepository;
    @Autowired
    JwtUtil(LoginLogRepository loginLogRepository, MemberRepository memberRepository) {
        this.loginLogRepository = loginLogRepository;
        this.memberRepository = memberRepository;
    }

    public String extractUsername(String token) { return extractClaim(token, Claims::getSubject);}
    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration);}
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(String username) {

        String token = null;
        Map<String, Object> claims = new HashMap<>();
        token = createToken(claims, username);
        if(token != null){
            Member member = memberRepository.findByUsername(username);
            loginLogRepository.save(new LoginLog(0, new Date(), member));
        }
        return token;
    }
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * expireSecond))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
