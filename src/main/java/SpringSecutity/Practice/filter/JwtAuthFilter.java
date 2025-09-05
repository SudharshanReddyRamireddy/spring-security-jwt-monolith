package SpringSecutity.Practice.filter;




import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import SpringSecutity.Practice.GlobalExceptionHandling.TokenExpiredException;
import SpringSecutity.Practice.service.JwtService;
import SpringSecutity.Practice.service.TokenBlockListService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;
    
    private final TokenBlockListService tokenBlockListService;
   

    public JwtAuthFilter(JwtService jwtService, TokenBlockListService tokenBlockListService) {
        this.jwtService = jwtService;
        this.tokenBlockListService = tokenBlockListService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

    	log.info("started doFilterChain......!");
        final String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }
        
        log.info("HEADER : " + header.toString());

        String token = header.substring(7);
        
     // 1️ Check blacklist
        log.info("IS TOKEN BLOCKLIST : " + tokenBlockListService.isTokenBlacklisted(token));
        if (tokenBlockListService.isTokenBlacklisted(token)) {
            throw new TokenExpiredException("TOKEN EXPIRED");
        }
        
        
        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            chain.doFilter(req, res);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var claims = jwtService.getAllClaims(token);

            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("roles");

            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(req, res);
    }
}
