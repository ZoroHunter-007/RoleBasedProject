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

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public ApiResponse<EmployeeResponseDTO>getMyData(Long empId){
        log.info("Employee get by ID:{}",empId);
        String currentUsername= SecurityContextHolder.getContext()
                .getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()->{
                    log.error("Employee Not Found! Please try again");
                    return new UserNotFoundException("Employee Not Found");
                });
        if(!isAdmin && !employee.getEmpEmail().equals(currentUsername)){
            throw new UnauthorizedException("Unauthorized access attempt");
        }
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Fetched",
                userMapper.convertResponse(employee)
        );
    }

    public ApiResponse<EmployeeResponseDTO>updateMyData(Long empId, UpdateRequestDTO dto){
        log.info("Employee Update by ID:{}",empId);
        String currentUsername=SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()->
                     new UserNotFoundException("Employee Not Found"));
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin && !employee.getEmpEmail().equals(currentUsername)){
            throw new UnauthorizedException("Unauthorized access attempt");
        }
        userMapper.mapEmployee(employee,dto,encoder);
        Employee updateEmp=employeeRepository.save(employee);
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Updated",
                userMapper.convertResponse(updateEmp)
        );
    }
    public ApiResponse<EmployeeResponseDTO>deleteMyData(Long empId){
        log.info("Delete Employee:{}",empId);
        String currentUsername= SecurityContextHolder.getContext()
                .getAuthentication().getName();
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        Employee employee=employeeRepository.findById(empId)
                .orElseThrow(()->
                        new UserNotFoundException("Employee Not Found")
                );
        if(!isAdmin && !employee.getEmpEmail().equals(currentUsername)){
            throw new UnauthorizedException("Unauthorized access attempt");
        }
        employeeRepository.delete(employee);
        return new ApiResponse<>(
                200,
                "Success",
                "Employee "+empId+" ID Successfully Deleted",
                null
        );
    }
}
