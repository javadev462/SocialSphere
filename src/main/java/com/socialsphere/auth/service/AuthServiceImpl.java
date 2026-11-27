package com.socialsphere.auth.service;

import com.socialsphere.auth.dto.Request;
import com.socialsphere.auth.dto.Response;
import com.socialsphere.auth.entity.User;
import com.socialsphere.auth.enums.Role;
import com.socialsphere.auth.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    //Mandatory dependencies should be final so it cannot be reassigned
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response register(Request request) {
        // Check if email already exists
        if (userRepo.existsByEmail(request.email())) {
            throw new RuntimeException("Email already registered");
        }

        // Check if username already exists
        if (userRepo.existsByUsername(request.username())) {
            throw new RuntimeException("Username already taken");
        }

        // Create User entity
        User user = modelMapper.map(request, User.class);

        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        // Save user
        User savedUser = userRepo.save(user);

        // Return response
        return new Response(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                "User registered successfully",
                savedUser.getCreatedAt()
        );
    }
}
