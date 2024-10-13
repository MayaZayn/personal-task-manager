package com.projects.personal_task_manager.user.service.Impl;

import com.projects.personal_task_manager.auth.SecurityUtils;
import com.projects.personal_task_manager.datatransform.DataTransformService;
import com.projects.personal_task_manager.errorhandling.ErrorMessages;
import com.projects.personal_task_manager.errorhandling.exceptions.AccessDeniedException;
import com.projects.personal_task_manager.errorhandling.exceptions.FieldRequiredException;
import com.projects.personal_task_manager.errorhandling.exceptions.InvalidInputException;
import com.projects.personal_task_manager.errorhandling.exceptions.NotFoundException;
import com.projects.personal_task_manager.user.model.dto.UserDTO;
import com.projects.personal_task_manager.user.model.enums.Role;
import com.projects.personal_task_manager.user.repository.UserRepository;
import com.projects.personal_task_manager.user.model.entity.User;
import com.projects.personal_task_manager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DataTransformService dataTransformService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtils securityUtils;

    @Override
    public List<UserDTO> getUsers() {
        validateUser();
        return userRepository.findAll().stream()
                .map(dataTransformService::toUserDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public UserDTO addUser(UserDTO userDTO) {
//        validateUser(userDTO);
//        User user = dataTransformService.toUser(userDTO);
//        userRepository.save(user);
//        return dataTransformService.toUserDTO(user);
//    }

    @Override
    public UserDTO getUser(Long id) {
        validateUser(id);
        return dataTransformService.toUserDTO(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND)));
    }

    @Override
    public void deleteUser(Long userId) {
        validateUser(userId);

        if (!userRepository.existsById(userId))
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);

        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        validateUser(userId);

        if (userDTO.getOldPassword() == null) {
            throw new FieldRequiredException(ErrorMessages.OLD_PASSWORD_REQUIRED);
        }

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(userDTO.getOldPassword(), user.getPassword())) {
            throw new InvalidInputException(ErrorMessages.INVALID_OLD_PASSWORD);
        }

        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail()))  {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getNewPassword() != null) {
            if (!passwordEncoder.matches(userDTO.getNewPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
            }
        }

        userRepository.save(user);

        return dataTransformService.toUserDTO(user);
    }

    private void validateUser(Long id) {
        User user = securityUtils.getUserFromContext();
        Long userId = user.getId();
        Role role = user.getRole();
        if (!Objects.equals(userId, id) && role == Role.USER)
            throw new AccessDeniedException(ErrorMessages.ACCESS_DENIED_TO_USER);
    }

    private void validateUser() {
        User user = securityUtils.getUserFromContext();
        Role role = user.getRole();
        if (role == Role.USER)
            throw new AccessDeniedException(ErrorMessages.ACCESS_DENIED_TO_USER);
    }
}
