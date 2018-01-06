package me.javaroad.mcloud.oauth.mapper;

import java.util.List;
import me.javaroad.mcloud.oauth.dto.request.UserRequest;
import me.javaroad.mcloud.oauth.dto.response.UserResponse;
import me.javaroad.mcloud.oauth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author heyx
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapRequestToEntity(UserRequest userRequest);

    UserResponse mapEntityToResponse(User user);

    List<UserResponse> mapEntityToResponse(List<User> users);
}
