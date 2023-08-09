package com.example.Spring.Security.service;

import com.example.Spring.Security.controller.AuthController;
import com.example.Spring.Security.dto.AuthResponse;
import com.example.Spring.Security.dto.LoginDto;
import com.example.Spring.Security.dto.RegisterDto;
import com.example.Spring.Security.entity.RoleEntity;
import com.example.Spring.Security.entity.UserEntity;
import com.example.Spring.Security.repository.RoleRepository;
import com.example.Spring.Security.repository.UserRepository;
import com.example.Spring.Security.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder passwordEncoder;


    @Override
    public AuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return com.example.Spring.Security.dto.AuthResponse.builder()
                .token(jwtTokenProvider.generateToken(authentication))
                .build();

    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail())) {
            return "Username or Email is already taken";
        }

        RoleEntity role = roleRepository.findByRolename("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        UserEntity user = UserEntity.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .otherName(registerDto.getOtherName())
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}
