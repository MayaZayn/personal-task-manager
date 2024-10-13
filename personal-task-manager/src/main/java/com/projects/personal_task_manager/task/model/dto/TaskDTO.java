package com.projects.personal_task_manager.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private Status status;
    private Category category;
    private Long userId;
}
