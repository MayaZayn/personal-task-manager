package com.projects.personal_task_manager.user.controller;

import com.projects.personal_task_manager.user.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserController {
    @GetMapping
    ResponseEntity<List<UserDTO>> getUsers();

//    @PostMapping
//    ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto);

    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> getUser(@PathVariable Long userId);

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDto);

//    @PatchMapping("/{userId}")
//    ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody UserPasswordDTO userPasswordDTO);
}
