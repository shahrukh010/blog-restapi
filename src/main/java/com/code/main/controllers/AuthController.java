package com.code.main.controllers;

import com.code.main.payload.JwtAuthResponse;
import com.code.main.payload.LoginDto;
import com.code.main.payload.RegisterDto;
import com.code.main.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {

        JwtAuthResponse response = new JwtAuthResponse();
        String token = authService.login(loginDto);
        response.setToken(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }

}
