package SpringSecutity.Practice.repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import SpringSecutity.Practice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
