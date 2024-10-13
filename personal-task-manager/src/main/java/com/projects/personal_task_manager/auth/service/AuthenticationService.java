package com.projects.personal_task_manager.auth.service;

import com.projects.personal_task_manager.auth.model.AuthenticationResponse;
import com.projects.personal_task_manager.auth.model.LoginRequest;
import com.projects.personal_task_manager.auth.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
