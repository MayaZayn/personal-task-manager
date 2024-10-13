package com.projects.personal_task_manager.auth.controller;

import com.projects.personal_task_manager.auth.model.AuthenticationResponse;
import com.projects.personal_task_manager.auth.model.LoginRequest;
import com.projects.personal_task_manager.auth.model.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {
    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request);
}
