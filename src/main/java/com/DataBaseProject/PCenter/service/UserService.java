package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.controller.UserController;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.UserDto;

import java.util.Optional;

public interface UserService {
    User save(UserDto userDto);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    User update(UserDto userDto);

    UserDto getUser(String email);

    void register(UserController.RegistrationRequest registrationRequest);
}
