package com.springboot.rolebasedproject.DTO;

import com.springboot.rolebasedproject.Entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class RegisterRequestDTO {

    @NotBlank(message = "Employee Name Can't be Blank")
    private String empName;

    @NotBlank(message = "Employee Email Can't be Blank")
    @Email
    private String empEmail;

    @NotBlank(message = "Password Can't be Blank")
    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Employee Salary Can't be Null")
    @Min(value = 1000,message = "Salary must be at least Rs.1000")
    private Double empSalary;

    @NotBlank(message = "Department Can't be Blank")
    private String empDepartment;

    @NotBlank(message = "Employee Position Can't be Blank")
    private String empPosition;

    @NotNull(message = "Role Can't be Null")
    private Role role;
}
