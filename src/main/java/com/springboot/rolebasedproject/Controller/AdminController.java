package com.springboot.rolebasedproject.Controller;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.UpdateRequestDTO;
import com.springboot.rolebasedproject.Response.ApiResponse;
import com.springboot.rolebasedproject.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>>getAllEmployees(){
        ApiResponse<List<EmployeeResponseDTO>>response=adminService.getAllEmployees();

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>getEmployeeById(
            @PathVariable Long empId
    ){
        ApiResponse<EmployeeResponseDTO>response=adminService.getEmployeeById(empId);

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>updateEmployeeById(
            @PathVariable Long empId, @Valid @RequestBody UpdateRequestDTO dto
            ){
        ApiResponse<EmployeeResponseDTO>response=adminService.updateEmployeeById(empId,dto);

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>deleteEmployeeById(
            @PathVariable Long empId
    ){
        ApiResponse<EmployeeResponseDTO>response=adminService.deleteEmployeeById(empId);

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }

}
