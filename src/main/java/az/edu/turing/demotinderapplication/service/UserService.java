package az.edu.turing.demotinderapplication.service;


import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.model.dto.request.UserRequest;
import az.edu.turing.demotinderapplication.model.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> findAllUsers();

    UserResponse findUserById(Long id);

    UserResponse saveUser(UserRequest userRequest);

    void deleteUser(Long id);

    Optional<UserEntity> findByUsername(String username);

    void likeUser(Long currentUserId, Long likedUserId);

    List<UserEntity> getLikedUsers(Long userId);
}
