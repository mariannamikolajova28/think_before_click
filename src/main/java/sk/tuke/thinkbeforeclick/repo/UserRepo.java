package sk.tuke.thinkbeforeclick.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.thinkbeforeclick.entity.UserEntity;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}