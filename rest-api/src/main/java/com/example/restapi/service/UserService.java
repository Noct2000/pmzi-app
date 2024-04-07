package com.example.restapi.service;

import com.example.restapi.dto.ChangePasswordRequestDto;
import com.example.restapi.model.Role;
import com.example.restapi.model.User;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserService extends CrudService<User> {
    Optional<User> findByUsername(String username);

    void changeUserPassword(
            String username,
            ChangePasswordRequestDto changePasswordRequestDto
    );

    User createNewUser(User user, Map<String, String> questionAnswerMap);

    void changeBlockedStatus(Long userId, Boolean status);

    User changeRoles(Long userId, Set<Role.RoleName> roleNames);
}
