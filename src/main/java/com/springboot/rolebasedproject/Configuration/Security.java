package com.springboot.rolebasedproject.Configuration;

import com.springboot.rolebasedproject.Entity.Employee;
import com.springboot.rolebasedproject.Exception.UserNotFoundException;
import com.springboot.rolebasedproject.Repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class Security {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(
                        auth-> auth.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/user/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(EmployeeRepository employeeRepository){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String empEmail) throws UsernameNotFoundException {
                Employee employee=employeeRepository.findByEmpEmail(empEmail)
                        .orElseThrow(()-> new UserNotFoundException("User not found"));

                return User.builder()
                        .username(employee.getEmpEmail())
                        .password(employee.getPassword())
                        .roles(employee.getRole().toString())
                        .build();
            }
        };
    }
}
