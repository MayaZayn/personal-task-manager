package com.projects.personal_task_manager.auth;

import com.projects.personal_task_manager.user.model.entity.User;
import com.projects.personal_task_manager.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityUtils {
    private final UserRepository userRepository;

    public User getUserFromContext() {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByUsername(username).get();
    }
}
