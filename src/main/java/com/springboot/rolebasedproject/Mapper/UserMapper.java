package com.springboot.rolebasedproject.Mapper;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.UpdateRequestDTO;
import com.springboot.rolebasedproject.Entity.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public void mapEmployee(Employee employee, UpdateRequestDTO dto, PasswordEncoder encoder){
        if(dto.getEmpName()!=null){
            employee.setEmpName(dto.getEmpName());
        }
        if(dto.getEmpEmail()!=null){
            employee.setEmpEmail(dto.getEmpEmail());
        }
        if(dto.getEmpDepartment()!=null){
            employee.setEmpDepartment(dto.getEmpDepartment());
        }
        if(dto.getEmpSalary()!=null){
            employee.setEmpSalary(dto.getEmpSalary());
        }
        if(dto.getEmpPosition()!=null){
            employee.setEmpPosition(dto.getEmpPosition());
        }
        if(dto.getRole()!=null){
            employee.setRole(dto.getRole());
        }
        if (dto.getPassword()!=null){
            employee.setPassword(encoder.encode(dto.getPassword()));
        }

    }

    public EmployeeResponseDTO convertResponse(Employee employee){
        return new EmployeeResponseDTO(
                employee.getEmpId(),
                employee.getEmpName(),
                employee.getEmpEmail(),
                employee.getEmpSalary(),
                employee.getEmpDepartment(),
                employee.getEmpPosition(),
                employee.getRole()
        );
    }
}
