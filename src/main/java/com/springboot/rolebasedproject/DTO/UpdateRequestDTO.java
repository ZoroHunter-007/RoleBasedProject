package com.springboot.rolebasedproject.DTO;

import com.springboot.rolebasedproject.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRequestDTO {


    private String empName;

    @Email
    private String empEmail;

    @Size(min = 6,message = "Password must be at least 6 characters")
    private String password;

    @Min(value = 1000,message = "Salary must be at least Rs.1000")
    private Double empSalary;


    private String empDepartment;

    private String empPosition;

    private Role role;
}
