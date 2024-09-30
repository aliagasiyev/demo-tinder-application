package az.edu.turing.demotinderapplication.domain.repository;

import az.edu.turing.demotinderapplication.domain.entity.LikedEntity;
import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikedEntity, Long> {
    List<LikedEntity> findByUser(UserEntity user);
    boolean existsByUserAndLikedUser(UserEntity user, UserEntity likedUser);

}
