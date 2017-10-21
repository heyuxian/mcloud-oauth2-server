package me.javaroad.oauth.service;

import java.util.Objects;
import me.javaroad.common.exception.DataConflictException;
import me.javaroad.common.exception.DataNotFoundException;
import me.javaroad.oauth.dto.request.SearchUserRequest;
import me.javaroad.oauth.dto.request.UserRequest;
import me.javaroad.oauth.dto.response.UserResponse;
import me.javaroad.oauth.entity.User;
import me.javaroad.oauth.mapper.UserMapper;
import me.javaroad.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyx
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
        UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createOrUpdate(UserRequest userRequest) {
        if(Objects.isNull(userRequest.getId())) {
            return createUser(userRequest);
        }
        return update(userRequest);
    }

    private UserResponse update(UserRequest userRequest) {
        User user = getUser(userRequest.getId());
        if (Objects.isNull(user)) {
            throw new DataNotFoundException("User[userId=%s] not found", userRequest.getId());
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        user = userRepository.save(user);
        return mapper.mapEntityToResponse(user);
    }

    private UserResponse createUser(UserRequest userRequest) {
        User user = getUser(userRequest.getUsername());
        if (Objects.nonNull(user)) {
            throw new DataConflictException("User[username=%s] already exists", userRequest.getUsername());
        }
        user = mapper.mapRequestToEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        user = userRepository.save(user);
        return mapper.mapEntityToResponse(user);
    }

    public Page<UserResponse> getAll(SearchUserRequest searchUserRequest, Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(mapper::mapEntityToResponse);
    }

    User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public UserResponse get(Long userId) {
        User user = getUser(userId);
        return mapper.mapEntityToResponse(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void delete(Long userId) {
        User user = getUser(userId);
        if(Objects.isNull(user)) {
            throw new DataNotFoundException("user[id=%s] not found", userId);
        }
        userRepository.delete(user);
    }

}
