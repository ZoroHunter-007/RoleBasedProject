package com.springboot.rolebasedproject.Controller;

import com.springboot.rolebasedproject.DTO.EmployeeResponseDTO;
import com.springboot.rolebasedproject.DTO.LoginRequestDTO;
import com.springboot.rolebasedproject.DTO.RegisterRequestDTO;
import com.springboot.rolebasedproject.Response.ApiResponse;
import com.springboot.rolebasedproject.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>registerEmployee(
            @Valid @RequestBody RegisterRequestDTO dto
            ){
        ApiResponse<EmployeeResponseDTO>response=authService.registerEmployee(dto);

        return new ResponseEntity<>(
                response, HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>>loginEmployee(
            @Valid @RequestBody LoginRequestDTO dto
            ){
        ApiResponse<EmployeeResponseDTO>response=authService.loginEmployee(dto);
        return new ResponseEntity<>(
                response, HttpStatus.valueOf(response.getStatusCode())
        );

    }
}
