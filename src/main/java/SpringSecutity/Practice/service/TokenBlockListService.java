package SpringSecutity.Practice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlockListService {

    private static final Logger log = LoggerFactory.getLogger(TokenBlockListService.class);

    // Thread-safe set to store blacklisted tokens
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    // Add token to blacklist
    public void blacklistToken(String token) {
        blacklist.add(token);
        log.info("TOKEN ADDED TO BLOCKLIST: {}", token);
    }

    // Check if token is blacklisted
    public boolean isTokenBlacklisted(String token) {
        boolean exists = blacklist.contains(token);
        log.info("Is token in blocklist: {}", exists);
        return exists;
    }

    // Expose the set for the scheduler
    public Set<String> getBlacklistSet() {
        return blacklist;
    }
}
