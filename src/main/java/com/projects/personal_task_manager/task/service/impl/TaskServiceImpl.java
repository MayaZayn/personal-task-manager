package com.projects.personal_task_manager.task.service.impl;

import com.projects.personal_task_manager.auth.SecurityUtils;
import com.projects.personal_task_manager.datatransform.DataTransformService;
import com.projects.personal_task_manager.errorhandling.*;
import com.projects.personal_task_manager.errorhandling.exceptions.AccessDeniedException;
import com.projects.personal_task_manager.errorhandling.exceptions.FieldRequiredException;
import com.projects.personal_task_manager.errorhandling.exceptions.NotFoundException;
import com.projects.personal_task_manager.task.model.entity.Task;
import com.projects.personal_task_manager.task.model.dto.TaskDTO;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import com.projects.personal_task_manager.task.repository.TaskRepository;
import com.projects.personal_task_manager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final DataTransformService dataTransformService;
    private final SecurityUtils securityUtils;

    @Override
    public List<TaskDTO> getTasks() {
        Long userId = securityUtils.getUserFromContext().getId();

        return taskRepository.findAllByUserId(userId).stream()
                .map(dataTransformService::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        validateTask(taskDTO);

        if (taskDTO.getCategory() == null)
            taskDTO.setCategory(Category.OTHER);
        if (taskDTO.getStatus() == null)
            taskDTO.setStatus(Status.NEW);

        Long userId = securityUtils.getUserFromContext().getId();

        taskDTO.setUserId(userId);
        Task task = dataTransformService.toTask(taskDTO);
        taskRepository.save(task);

        return dataTransformService.toTaskDTO(task);
    }

    @Override
    public TaskDTO getTask(Long taskId) {
        validateAccessToTask(taskId);

        return dataTransformService.toTaskDTO(taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.TASK_NOT_FOUND)));
    }

    @Override
    public void deleteTask(Long taskId) {
        validateAccessToTask(taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isEmpty())
            throw new NotFoundException(ErrorMessages.TASK_NOT_FOUND);

        Task task = taskOptional.get();

        validateAccessToTask(taskId);

        if (taskDTO.getTitle() != null)
            task.setTitle(taskDTO.getTitle());
        if (taskDTO.getDescription() != null)
            task.setDescription(taskDTO.getDescription());
        if (taskDTO.getDueDate() != null)
            task.setDueDate(taskDTO.getDueDate());
        if (taskDTO.getStatus() != null)
            task.setStatus(taskDTO.getStatus());
        if (taskDTO.getCategory() != null)
            task.setCategory(taskDTO.getCategory());

        taskRepository.save(task);

        return dataTransformService.toTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getFilteredTasks(Status status, Category category) {
        Long userId = securityUtils.getUserFromContext().getId();

        return taskRepository.findAllByStatusAndCategoryAndUserId(status, category, userId).stream()
                .map(dataTransformService::toTaskDTO)
                .collect(Collectors.toList());
    }

    private void validateTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null) {
            throw new FieldRequiredException(ErrorMessages.TITLE_REQUIRED);
        }
    }

    private void validateAccessToTask(Long taskId) {
        Long userId = securityUtils.getUserFromContext().getId();
        for (Task task : taskRepository.findAllByUserId(userId)) {
            if (Objects.equals(task.getId(), taskId))
                return;
        }
        throw new AccessDeniedException(ErrorMessages.ACCESS_DENIED_TO_TASK);
    }
}
