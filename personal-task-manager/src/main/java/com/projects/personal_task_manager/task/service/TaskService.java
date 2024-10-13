package com.projects.personal_task_manager.task.service;

import com.projects.personal_task_manager.task.model.dto.TaskDTO;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks();

    TaskDTO addTask(TaskDTO taskDto);

    TaskDTO getTask(Long taskId);

    void deleteTask(Long taskId);

    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);

    List<TaskDTO> getFilteredTasks(Status status, Category category);
}
