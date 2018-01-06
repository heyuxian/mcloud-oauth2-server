package me.javaroad.mcloud.oauth.mapper;



import static org.assertj.core.api.Assertions.assertThat;

import me.javaroad.mcloud.oauth.dto.response.UserResponse;
import me.javaroad.mcloud.oauth.entity.User;
import org.junit.Test;

/**
 * @author heyx
 */
public class UserMapperTest {

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    public void mapEntityToResponse() throws Exception {
        User user = new User();
        user.setUsername("user");
        user.setId(1L);
        UserResponse userResponse = userMapper.mapEntityToResponse(user);
        assertThat(userResponse.getId()).isEqualTo(user.getId());
    }

    @Test
    public void mapNullEntityToResponse() throws Exception {
        User user = null;
        UserResponse userResponse = userMapper.mapEntityToResponse(user);
        assertThat(userResponse).isNull();
    }

}