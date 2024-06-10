package com.code.main.services;

import com.code.main.payload.LoginDto;
import com.code.main.payload.RegisterDto;

public interface AuthService {

    public String login(LoginDto loginDto);

    public String register(RegisterDto registerDto);
}
