package az.edu.turing.demotinderapplication.mapper;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.model.dto.request.UserRequest;
import az.edu.turing.demotinderapplication.model.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(target = "lastLoginAt", ignore = true),
            @Mapping(target = "lastActiveAt",ignore = true),
            @Mapping(target = "profilePhoto", ignore = true),
            @Mapping(target = "liked", ignore = true),
    })
    UserEntity toEntity(UserRequest userRequest);
    UserResponse toResponse(UserEntity user);
}
