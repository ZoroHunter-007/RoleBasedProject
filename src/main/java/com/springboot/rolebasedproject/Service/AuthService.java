package com.springboot.rolebasedproject.Service;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.LoginRequestDTO;
import com.springboot.rolebasedproject.DTO.RegisterRequestDTO;
import com.springboot.rolebasedproject.Entity.Employee;
import com.springboot.rolebasedproject.Exception.EmailExistsException;
import com.springboot.rolebasedproject.Exception.InvalidCredentialException;
import com.springboot.rolebasedproject.Exception.UserNotFoundException;
import com.springboot.rolebasedproject.Mapper.UserMapper;
import com.springboot.rolebasedproject.Repository.EmployeeRepository;
import com.springboot.rolebasedproject.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public ApiResponse<EmployeeResponseDTO>registerEmployee(RegisterRequestDTO dto){
        log.info("Register Employee using Email:{}",dto.getEmpEmail());

        if(employeeRepository.findByEmpEmail(dto.getEmpEmail()).isPresent()){
            log.error("Email Already Exists:{}",dto.getEmpEmail());
            throw new EmailExistsException("Email Already Exists! Please Try Again");
        }
        Employee employee=new Employee();
        employee.setEmpName(dto.getEmpName());
        employee.setEmpEmail(dto.getEmpEmail());
        employee.setPassword(encoder.encode(dto.getPassword()));
        employee.setEmpSalary(dto.getEmpSalary());
        employee.setEmpDepartment(dto.getEmpDepartment());
        employee.setEmpPosition(dto.getEmpPosition());
        employee.setRole(dto.getRole());

        Employee savedEmp=employeeRepository.save(employee);

        return new ApiResponse<>(
                201,
                "Created",
                "Register Employee Successfully",
                userMapper.convertResponse(savedEmp)
        );

    }

    public ApiResponse<EmployeeResponseDTO>loginEmployee(LoginRequestDTO dto){
        log.info("Login With Employee Email:{}",dto.getEmpEmail());
        Employee employee=employeeRepository.findByEmpEmail(dto.getEmpEmail())
                .orElseThrow(()->{
                    log.error("User Not Found! Please Enter Valid Credential");
                    return new UserNotFoundException("User Not Found");
                });
        if(!encoder.matches(dto.getPassword(), employee.getPassword())){
            log.error("Email and Password Incorrect:{}",dto.getEmpEmail());
            throw new InvalidCredentialException("Email and Password Incorrect");
        }
        return new ApiResponse<>(
                200,
                "Success",
                "Login Employee Successfully",
                userMapper.convertResponse(employee)
        );
    }
}
