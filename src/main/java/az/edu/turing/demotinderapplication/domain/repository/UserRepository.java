package az.edu.turing.demotinderapplication.domain.repository;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
