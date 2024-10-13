package com.projects.personal_task_manager.task.repository;

import com.projects.personal_task_manager.task.model.entity.Task;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);
    List<Task> findAllByStatusAndCategoryAndUserId(Status status, Category category, Long userId);
}
