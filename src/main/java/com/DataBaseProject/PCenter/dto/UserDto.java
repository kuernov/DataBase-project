package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.Address;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Size(min = 3, message = "First name contains min 3 characters")
    private String firstName;
    @Size(min = 3, message = "Last name contains min 3 characters")
    private String lastName;
    private String email;
    @Size(min = 5, message = "Password contains min 5 characters")
    private String password;
    @Size(min = 9, message = "Password contains min 9 characters")
    private String phoneNumber;
    private Address address;
}
