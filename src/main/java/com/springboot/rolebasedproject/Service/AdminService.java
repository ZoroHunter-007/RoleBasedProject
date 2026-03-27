package com.springboot.rolebasedproject.Service;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.UpdateRequestDTO;
import com.springboot.rolebasedproject.Entity.Employee;
import com.springboot.rolebasedproject.Exception.UnauthorizedException;
import com.springboot.rolebasedproject.Exception.UserNotFoundException;
import com.springboot.rolebasedproject.Mapper.UserMapper;
import com.springboot.rolebasedproject.Repository.EmployeeRepository;
import com.springboot.rolebasedproject.Response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public ApiResponse<List<EmployeeResponseDTO>>getAllEmployees(){
        log.info("All Employees Fetched by ADMIN");
        List<EmployeeResponseDTO>list=employeeRepository.findAll()
                .stream()
                .map(userMapper::convertResponse)
                .toList();
        return new ApiResponse<>(
                200,
                "Success",
                "All Employee Fetched Successfully",
                list
        );
    }
    public ApiResponse<EmployeeResponseDTO>getEmployeeById(Long empId){
        log.info("Employee Fetched ID by ADMIN:{}",empId);
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()->{
                    log.error("Employee ID not Found:{}",empId);
                   return new UserNotFoundException("Employee Not Found");
                }

        );
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Fetched",
                userMapper.convertResponse(employee)
        );
    }
    public ApiResponse<EmployeeResponseDTO>updateEmployeeById(Long empId, UpdateRequestDTO dto){
        log.info("Employee Update ID by ADMIN:{}",empId);
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()-> {
                    log.error("Employee not Found with ID:{}",empId);
                   return new UserNotFoundException("Employee Not Found");
                });
        userMapper.mapEmployee(employee,dto,encoder);
        Employee updateEmp=employeeRepository.save(employee);
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Updated",
                userMapper.convertResponse(updateEmp)
        );
    }
    public ApiResponse<EmployeeResponseDTO>deleteEmployeeById(Long empId){
        log.info("Delete Employee ID by ADMIN:{}",empId);
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()-> {
                    log.error("Employee ID not found");
                 return new UserNotFoundException("Employee Not Found");
                });
        employeeRepository.delete(employee);
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Deleted",
                null
        );
    }


}
