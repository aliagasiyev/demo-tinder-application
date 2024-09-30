package az.edu.turing.demotinderapplication.controller;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.service.LikedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/likes")
public class LikeController {

    private final LikedService likedService;

    @PostMapping("/{currentUserId}/like/{likedUserId}")
    public ResponseEntity<String> likeUser(@PathVariable Long currentUserId, @PathVariable Long likedUserId) {
        likedService.likeUser(currentUserId, likedUserId);
        return ResponseEntity.ok("User liked successfully");
    }

    @PostMapping("/{currentUserId}/dislike/{dislikedUserId}")
    public ResponseEntity<String> dislikeUser(@PathVariable Long currentUserId, @PathVariable Long dislikedUserId) {
        likedService.dislikeUser(currentUserId, dislikedUserId);
        return ResponseEntity.ok("User disliked successfully");
    }

    @GetMapping("/{userId}/liked")
    public ResponseEntity<List<UserEntity>> getLikedUsers(@PathVariable Long userId) {
        return ResponseEntity.ok(likedService.getLikedUsers(userId));
    }

    @GetMapping("/{currentUserId}/next-profile")
    public ResponseEntity<UserEntity> getNextProfile(@PathVariable Long currentUserId) {
        return ResponseEntity.ok(likedService.getNextProfile(currentUserId));
    }
}
