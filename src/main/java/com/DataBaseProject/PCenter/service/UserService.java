package com.DataBaseProject.PCenter.service;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.user.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
