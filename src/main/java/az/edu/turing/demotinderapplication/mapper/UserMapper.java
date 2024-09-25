package az.edu.turing.demotinderapplication.mapper;

import az.edu.turing.demotinderapplication.domain.entity.UserEntity;
import az.edu.turing.demotinderapplication.model.dto.request.UserRequest;
import az.edu.turing.demotinderapplication.model.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);



    UserEntity toEntity(UserRequest userRequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name",ignore = true)
    @Mapping(target ="photoUrl", ignore = true)

    UserResponse toResponse(UserEntity user);

}
