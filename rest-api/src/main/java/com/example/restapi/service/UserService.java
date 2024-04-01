package com.example.restapi.service;

import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.model.User;
import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByUsername(String username);

    void changeUserPassword(
            String username,
            ChangePasswordRequestDto changePasswordRequestDto
    );

    User createNewUser(User user);

    void changeBlockedStatus(Long userId, Boolean status);
}
