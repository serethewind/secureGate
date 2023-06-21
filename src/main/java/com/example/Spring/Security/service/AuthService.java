package com.example.Spring.Security.service;

import com.example.Spring.Security.dto.AuthResponse;
import com.example.Spring.Security.dto.LoginDto;
import com.example.Spring.Security.dto.RegisterDto;

public interface AuthService {

    AuthResponse login (LoginDto loginDto);

    String register (RegisterDto registerDto);
}
