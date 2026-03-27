package com.springboot.rolebasedproject.Controller;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.UpdateRequestDTO;
import com.springboot.rolebasedproject.Response.ApiResponse;
import com.springboot.rolebasedproject.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>getMyData(
            @PathVariable Long empId
    ){
        ApiResponse<EmployeeResponseDTO>response=userService.getMyData(empId);

        return new ResponseEntity<>(
                response, HttpStatus.valueOf(response.getStatusCode())
        );
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>updateMyData(
            @PathVariable Long empId, @Valid @RequestBody UpdateRequestDTO dto
            ){
        ApiResponse<EmployeeResponseDTO>response=userService.updateMyData(empId,dto);

        return new ResponseEntity<>(
                response, HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>deleteMyData(
            @PathVariable Long empId
    ){
        ApiResponse<EmployeeResponseDTO>response=userService.deleteMyData(empId);

        return new ResponseEntity<>(
                response, HttpStatus.valueOf(response.getStatusCode())
        );
    }
}
