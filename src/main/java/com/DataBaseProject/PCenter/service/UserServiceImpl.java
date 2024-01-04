package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.controller.UserController;
import com.DataBaseProject.PCenter.data.Role;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.UserDto;
import com.DataBaseProject.PCenter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserDto getUser(String email) {
        UserDto userDto = new UserDto();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    @Override
    public void register(UserController.RegistrationRequest request) {
        var user = User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                // MUST BE HASHED!!!
                .password(request.password())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .roles(List.of(Role.USER.name()))
                .orders(emptyList())
                .build();

        userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setFirstname(userDto.getFirstName());
        user.setLastname(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        return userRepository.save(user);
    }

}
