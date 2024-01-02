package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.UserDto;

public interface UserService {
    User save(UserDto userDto);
    User findByEmail(String email);
    User update(UserDto userDto);
    User changePass(UserDto userDto);
    UserDto getUser(String email);

}
