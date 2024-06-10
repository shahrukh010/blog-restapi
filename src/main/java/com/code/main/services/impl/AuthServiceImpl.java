package com.code.main.services.impl;

import com.code.main.exception.BlogApiException;
import com.code.main.models.Role;
import com.code.main.models.User;
import com.code.main.payload.LoginDto;
import com.code.main.payload.RegisterDto;
import com.code.main.repository.RoleRepository;
import com.code.main.repository.UserRepository;
import com.code.main.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return "Login successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {

        //check username is exists or not.
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "user is already exists");
        }
        //check email is exists or not.
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "email is already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(String.valueOf(registerDto.getPassword())));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("admin").get();
        user.setRoles(roles);
        userRepository.save(user);
        return "user register successfully";

    }
}
