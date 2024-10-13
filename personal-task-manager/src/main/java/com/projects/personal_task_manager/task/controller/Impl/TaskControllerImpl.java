package com.projects.personal_task_manager.task.controller.Impl;

import com.projects.personal_task_manager.task.controller.TaskController;
import com.projects.personal_task_manager.task.model.dto.TaskDTO;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import com.projects.personal_task_manager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskControllerImpl implements TaskController {
    private final TaskService taskServiceImpl;

    @Override
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return new ResponseEntity<>(taskServiceImpl.getTasks(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDto) {
        return new ResponseEntity<>(taskServiceImpl.addTask(taskDto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(taskServiceImpl.getTask(taskId), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskServiceImpl.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(taskServiceImpl.updateTask(taskId, taskDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<List<TaskDTO>> getFilteredTasks(@RequestParam(defaultValue = "NEW", required = false)
                                                              Status status,
                                                          @RequestParam(defaultValue = "OTHER", required = false)
                                                              Category category) {
        return new ResponseEntity<>(taskServiceImpl.getFilteredTasks(status, category), HttpStatus.OK);
    }
}
