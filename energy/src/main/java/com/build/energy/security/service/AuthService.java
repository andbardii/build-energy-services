package com.build.energy.security.service;

import com.build.energy.security.payload.LoginDto;
import com.build.energy.security.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
