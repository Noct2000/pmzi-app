package com.example.restapi.service;

import com.example.restapi.model.User;
import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByUsername(String username);
}
