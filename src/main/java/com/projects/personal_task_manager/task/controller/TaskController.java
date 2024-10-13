package com.projects.personal_task_manager.task.controller;

import com.projects.personal_task_manager.task.model.dto.TaskDTO;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tasks")
public interface TaskController {
    @GetMapping
    ResponseEntity<List<TaskDTO>> getTasks();

    @PostMapping
    ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDto);

    @GetMapping("/{taskId}")
    ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId);

    @DeleteMapping("/{taskId}")
    ResponseEntity<Void> deleteTask(@PathVariable Long taskId);

    @PutMapping("/{taskId}")
    ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO);

    @GetMapping("/filter")
    ResponseEntity<List<TaskDTO>> getFilteredTasks(@RequestParam Status status, @RequestParam Category category);
}
