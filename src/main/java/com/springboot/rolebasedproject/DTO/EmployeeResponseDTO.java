package com.springboot.rolebasedproject.DTO;

import com.springboot.rolebasedproject.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Long empId;
    private String empName;
    private String empEmail;
    private Double empSalary;
    private String empDepartment;
    private String empPosition;
    private Role role;
}
