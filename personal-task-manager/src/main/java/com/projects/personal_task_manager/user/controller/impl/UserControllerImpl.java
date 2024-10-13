package com.projects.personal_task_manager.user.controller.impl;

import com.projects.personal_task_manager.user.controller.UserController;
import com.projects.personal_task_manager.user.model.dto.UserDTO;
import com.projects.personal_task_manager.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerImpl implements UserController {
    private final UserService userServiceImpl;

    @Override
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }

//    @Override
//    @PostMapping
//    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
//        return new ResponseEntity<>(userServiceImpl.addUser(userDto), HttpStatus.CREATED);
//    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userServiceImpl.getUser(userId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userServiceImpl.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDto) {
        return new ResponseEntity<>(userServiceImpl.updateUser(userId, userDto), HttpStatus.OK);
    }

//    @Override
//    @PatchMapping("/{userId}/password")
//    public ResponseEntity<Void> updatePassword(@PathVariable Long userId, @RequestBody UserPasswordDTO userPasswordDTO) {
//        userServiceImpl.updatePassword(userId, userPasswordDTO);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
