package com.springboot.rolebasedproject.Handler;

import com.springboot.rolebasedproject.Exception.EmailExistsException;
import com.springboot.rolebasedproject.Exception.InvalidCredentialException;
import com.springboot.rolebasedproject.Exception.UnauthorizedException;
import com.springboot.rolebasedproject.Exception.UserNotFoundException;
import com.springboot.rolebasedproject.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ApiResponse<Object>>handleEmailExistsException(EmailExistsException e){
        ApiResponse<Object>response=new ApiResponse<>(
                409,
                "Error",
                "Email Already Exists! Please try Again",
                e.getMessage()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>>handleUserNotFoundException(UserNotFoundException ue){
        ApiResponse<Object>response=new ApiResponse<>(
                404,
                "Error",
                "User Not Found! Please try Again",
                ue.getMessage()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>>handleUnauthorizedException(UnauthorizedException ue){
        ApiResponse<Object>response=new ApiResponse<>(
                403,
                "Error",
                "You are not Authorized for this page",
                ue.getMessage()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(response.getStatusCode())
        );

    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiResponse<Object>>handleInvalidCredentialException(InvalidCredentialException ie){
        ApiResponse<Object>response=new ApiResponse<>(
                401,
                "Error",
                "Please Enter valid credential and Try Again!",
                ie.getMessage()
        );

        return new ResponseEntity<>(
                response,HttpStatus.valueOf(response.getStatusCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>>handleInvalidArgumentException(MethodArgumentNotValidException me){
        List<String>errors=me.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(er-> er.getField()+" : "+ er.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest()
                .body(
                        new ApiResponse<>(
                                400,
                                "Error",
                                "Invalid Argument Passed",
                                errors
                        )
                );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>>handleInternalError(Exception e){
        return ResponseEntity.internalServerError()
                .body(
                        new ApiResponse<>(
                                500,
                                "Error",
                                "Something went Wrong!",
                                e.getMessage()
                        )
                );
    }
}
