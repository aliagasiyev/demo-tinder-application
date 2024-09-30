package az.edu.turing.demotinderapplication.service.impl;

import az.edu.turing.demotinderapplication.domain.entity.LikedEntity;
import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.domain.repository.LikeRepository;
import az.edu.turing.demotinderapplication.domain.repository.UserRepository;
import az.edu.turing.demotinderapplication.exception.ResourceNotFoundException;
import az.edu.turing.demotinderapplication.mapper.UserMapper;
import az.edu.turing.demotinderapplication.model.dto.request.UserRequest;
import az.edu.turing.demotinderapplication.model.dto.response.UserResponse;
import az.edu.turing.demotinderapplication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LikeRepository likeRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.likeRepository = likeRepository;
    }


    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void likeUser(Long currentUserId, Long likedUserId) {
        UserEntity currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + currentUserId + " not found"));

        UserEntity likedUser = userRepository.findById(likedUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + likedUserId + " not found"));

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
                .collect(Collectors.toList());

    }
}