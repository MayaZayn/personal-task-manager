package com.projects.personal_task_manager.user.service.Impl;

import com.projects.personal_task_manager.auth.SecurityUtils;
import com.projects.personal_task_manager.datatransform.DataTransformService;
import com.projects.personal_task_manager.user.model.dto.UserDTO;
import com.projects.personal_task_manager.user.model.entity.User;
import com.projects.personal_task_manager.user.model.enums.Role;
import com.projects.personal_task_manager.user.repository.UserRepository;
import com.projects.personal_task_manager.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DataTransformService dataTransformService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SecurityUtils securityUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsersShouldReturnListOfUserDTOs() {
        // Given
//        List<UserDTO> userDTOs = new ArrayList<>();
//        UserDTO userDTO = new UserDTO(
//                1L,
//                "username",
//                "email@mail.com",
//                null,
//                null,
//                Role.USER
//        );
//        userDTOs.add(userDTO);
//
//        List<User> users = new ArrayList<>();
//        User user = new User(
//                1L,
//                "username",
//                "email@mail.com",
//                "password",
//                Role.USER
//        );
//        users.add(user);
//
//        when(userRepository.findAll()).thenReturn(users);
//        when(dataTransformService.toUserDTO(user)).thenReturn(userDTO);
//
//        List<UserDTO> returnedUserDTOs = userService.getUsers();
//
//        assertEquals(userDTOs, returnedUserDTOs);
//        verify(userRepository, times(1)).findAll();
//        verify(dataTransformService, times(1)).toUserDTO(user);
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}