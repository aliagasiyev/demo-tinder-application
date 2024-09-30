package az.edu.turing.demotinderapplication.service.impl;

import az.edu.turing.demotinderapplication.domain.entity.LikedEntity;
import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.domain.repository.LikeRepository;
import az.edu.turing.demotinderapplication.domain.repository.UserRepository;
import az.edu.turing.demotinderapplication.exception.ResourceNotFoundException;
import az.edu.turing.demotinderapplication.service.LikedService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikedService {

    private LikeRepository likeRepository;

    private UserRepository userRepository;

    @Override
    public void likeUser(Long currentUserId, Long likedUserId) {
        UserEntity currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + currentUserId + " not found"));

        UserEntity likedUser = userRepository.findById(likedUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + likedUserId + " not found"));

        // Check if user already liked/disliked
        if (likeRepository.existsByUserAndLikedUser(currentUser, likedUser)) {
            throw new IllegalStateException("You already liked/disliked this user");
        }

        LikedEntity like = new LikedEntity();
        like.setUser(currentUser);
        like.setLikedUser(likedUser);
        like.setLikedAt(LocalDateTime.now());
        likeRepository.save(like);
    }

    @Override
    public List<UserEntity> getLikedUsers(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        return likeRepository.findByUser(user).stream()
                .map(LikedEntity::getLikedUser)
                .collect(Collectors.toList()).reversed();
    }
    @Override
    public void dislikeUser(Long currentUserId, Long dislikedUserId) {
        UserEntity currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + currentUserId + " not found"));

        UserEntity dislikedUser = userRepository.findById(dislikedUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + dislikedUserId + " not found"));

        // Check if user already disliked
        if (likeRepository.existsByUserAndLikedUser(currentUser, dislikedUser)) {
            throw new IllegalStateException("You already liked/disliked this user");
        }

        LikedEntity dislike = new LikedEntity();
        dislike.setUser(currentUser);
        dislike.setLikedUser(dislikedUser);
        dislike.setLikedAt(LocalDateTime.now());
        likeRepository.save(dislike);
    }

    // Method to get next profile (just as a basic implementation idea)
    public UserEntity getNextProfile(Long currentUserId) {
        List<UserEntity> allUsers = userRepository.findAll();
        List<UserEntity> likedOrDislikedUsers = getLikedUsers(currentUserId);

        return allUsers.stream()
                .filter(user -> !likedOrDislikedUsers.contains(user) && !user.getId().equals(currentUserId))
                .findFirst()
                .orElse(null); // Or throw an exception if no more profiles
    }

}
