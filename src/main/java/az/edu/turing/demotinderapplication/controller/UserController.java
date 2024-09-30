package az.edu.turing.demotinderapplication.controller;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.model.dto.request.UserRequest;
import az.edu.turing.demotinderapplication.model.dto.response.UserResponse;
import az.edu.turing.demotinderapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{currentUserId}/like/{likedUserId}")
    public ResponseEntity<String> likeUser(@PathVariable Long currentUserId, @PathVariable Long likedUserId) {
        userService.likeUser(currentUserId, likedUserId);
        return ResponseEntity.ok("User liked successfully");
    }

    @GetMapping("/{userId}/liked")
    public List<UserEntity> getLikedUsers(@PathVariable Long userId) {
        return userService.getLikedUsers(userId);
    }

}
