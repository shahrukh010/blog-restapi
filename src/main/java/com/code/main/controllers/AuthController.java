package com.code.main.controllers;

import com.code.main.payload.JwtAuthResponse;
import com.code.main.payload.LoginDto;
import com.code.main.payload.RegisterDto;
import com.code.main.services.AuthService;
import com.code.main.services.EmailService;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private AuthService authService;
    private EmailService emailService;

    public AuthController(AuthService authService, EmailService emailService) {
        this.authService = authService;
        this.emailService = emailService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String email) {
        String otp = authService.generateOtp(email);
        //otpStorageService.storeOtp(email, otp);
        emailService.sendOtpEmail(email, otp);
        return ResponseEntity.ok("OTP sent to email");
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
