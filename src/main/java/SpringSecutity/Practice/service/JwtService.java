package SpringSecutity.Practice.service;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;


@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.exp-minutes}")
    private long expMinutes;

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate token with all roles
    public String generateToken(UserDetails user) {
        long now = System.currentTimeMillis();

        var roles = user.getAuthorities().stream()
                .map(auth -> auth.getAuthority()) // ROLE_ADMIN, ROLE_CUSTOMER, etc.
                .toList();

//							        List<String> roles = new ArrayList<>();
//							        for (GrantedAuthority auth : user.getAuthorities()) {
//							            roles.add(auth.getAuthority());
//							        }
        
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roles)   // store roles
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expMinutes * 60_000))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parser(token).getBody().getSubject();
    }

    public Claims getAllClaims(String token) {
        return parser(token).getBody();
    }

    private Jws<Claims> parser(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
    }
}
