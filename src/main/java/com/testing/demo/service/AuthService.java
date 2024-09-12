package com.testing.demo.service;

import com.testing.demo.dto.LoginDto;
import com.testing.demo.dto.RegisterDto;
import com.testing.demo.model.UserEntity;

public interface AuthService {
    UserEntity registerUser(RegisterDto registerDto);
}
