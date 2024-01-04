package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.config.JwtService;
import com.DataBaseProject.PCenter.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/registration")
    public void register(@RequestBody @Validated RegistrationRequest request) {
        userService.register(request);
    }

    public record RegistrationRequest(String firstname, String lastname, String email, String password,
                                      String phoneNumber, String address
    ) {}

    @PostMapping("/login")
    public LoginResponse logIn(@RequestBody @Validated LoginRequest request) {
        // move it all to a service
        // password MUST be hashed
        return userService.findByEmailAndPassword(request.email(), request.password())
                .map(jwtService::generateToken)
                .map(LoginResponse::new)
                // return proper response instead of just throwing
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    public record LoginRequest(@NotBlank String email, @NotBlank String password) {}

    public record LoginResponse(String token) {}

    @GetMapping("/me")
    @RolesAllowed("USER")
    public MeResponse logIn(Principal principal) {
        return userService.findByEmail(principal.getName())
                .map(user -> new MeResponse(user.getFirstname(), user.getLastname()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public record MeResponse(String firstName, String lastName) {
    }

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    public void admin() {
        // dummy endpoint to test roles
    }

}
