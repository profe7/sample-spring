package com.testing.demo.service.Auth;

import com.testing.demo.model.dto.RegisterDto;
import com.testing.demo.model.entity.UserEntity;

public interface AuthService {
    UserEntity registerUser(RegisterDto registerDto);
}
