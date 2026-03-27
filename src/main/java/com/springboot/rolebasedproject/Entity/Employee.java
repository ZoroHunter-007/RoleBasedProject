package com.springboot.rolebasedproject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(name = "EmployeeName",nullable = false)
    private String empName;

    @Column(name = "EmployeeEmail",nullable = false,unique = true)
    private String empEmail;

    @Column(name = "Password",nullable = false)
    private String password;

    @Column(name = "EmployeeSalary",nullable = false)
    private Double empSalary;

    @Column(name = "Department",nullable = false)
    private String empDepartment;

    @Column(name = "EmployeePosition",nullable = false)
    private String empPosition;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role",nullable = false)
    private Role role;
}
