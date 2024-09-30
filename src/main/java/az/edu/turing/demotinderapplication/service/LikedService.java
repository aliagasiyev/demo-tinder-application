package az.edu.turing.demotinderapplication.service;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;

import java.util.List;

public interface LikedService {

    void likeUser(Long currentUserId, Long likedUserId);


    void dislikeUser(Long currentUserId, Long dislikedUserId);

    List<UserEntity> getLikedUsers(Long userId);

    UserEntity getNextProfile(Long currentUserId);
}

