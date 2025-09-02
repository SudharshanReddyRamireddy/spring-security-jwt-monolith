package SpringSecutity.Practice.model;


import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    // Use Enum for roles for type safety
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // --- UserDetails mapping ---
    /* this GrantedAuthority -> getAuthorities() is used in jwtService while generating Token,
     * while Generating Token, Roles will include in the token, because of @PreAuthorize() will check ROLE From Token only
     */
    
    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name())); // Why Prefix "ROLE_" added to role, 
    }
    
    

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // Enum for roles
    public enum Role {
        ADMIN,
        CUSTOMER,
        USER
    }
}
