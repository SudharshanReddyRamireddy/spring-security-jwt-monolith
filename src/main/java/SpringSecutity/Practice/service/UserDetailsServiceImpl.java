package SpringSecutity.Practice.service;


import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import SpringSecutity.Practice.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    public UserDetailsServiceImpl(UserRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                   .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
