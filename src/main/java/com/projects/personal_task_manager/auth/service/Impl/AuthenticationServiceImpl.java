package com.projects.personal_task_manager.auth.service.Impl;

import com.projects.personal_task_manager.auth.model.AuthenticationResponse;
import com.projects.personal_task_manager.auth.model.LoginRequest;
import com.projects.personal_task_manager.auth.model.RegisterRequest;
import com.projects.personal_task_manager.auth.service.AuthenticationService;
import com.projects.personal_task_manager.config.service.JwtService;
import com.projects.personal_task_manager.errorhandling.ErrorMessages;
import com.projects.personal_task_manager.errorhandling.exceptions.AlreadyExistsException;
import com.projects.personal_task_manager.errorhandling.exceptions.FieldRequiredException;
import com.projects.personal_task_manager.errorhandling.exceptions.InvalidInputException;
import com.projects.personal_task_manager.errorhandling.exceptions.NotFoundException;
import com.projects.personal_task_manager.user.model.entity.User;
import com.projects.personal_task_manager.user.model.enums.Role;
import com.projects.personal_task_manager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        validateRegisterRequest(request);

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.UNEXPECTED_ERROR);
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        validateLoginRequest(request);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidInputException(ErrorMessages.INVALID_CREDENTIALS);
        }

        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().length() < 3) {
            throw new InvalidInputException(ErrorMessages.INVALID_USERNAME);
        }

        if (request.getEmail() == null || !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidInputException(ErrorMessages.INVALID_EMAIL);
        }

        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new InvalidInputException(ErrorMessages.INVALID_PASSWORD);
        }
    }

    private void validateLoginRequest(LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new FieldRequiredException(ErrorMessages.CREDENTIALS_REQUIRED);
        }
    }
}
