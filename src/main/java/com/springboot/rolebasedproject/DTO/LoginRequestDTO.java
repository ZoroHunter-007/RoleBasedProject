package com.springboot.rolebasedproject.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Employee Email Can't be Blank")
    @Email
    private String empEmail;

    @NotBlank(message = "Password Can't be Blank")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;
}
