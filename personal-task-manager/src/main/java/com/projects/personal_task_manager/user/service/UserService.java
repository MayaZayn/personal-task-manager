package com.projects.personal_task_manager.user.service;

import com.projects.personal_task_manager.user.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

//    UserDTO addUser(UserDTO userDto);

    UserDTO getUser(Long id);

    void deleteUser(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDto);

//    void updatePassword(Long userId, UserPasswordDTO password);
}
