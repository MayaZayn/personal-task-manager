package com.projects.personal_task_manager.datatransform;

import com.projects.personal_task_manager.task.model.entity.Task;
import com.projects.personal_task_manager.task.model.dto.TaskDTO;
import com.projects.personal_task_manager.task.model.enums.Category;
import com.projects.personal_task_manager.task.model.enums.Status;
import com.projects.personal_task_manager.user.model.entity.User;
import com.projects.personal_task_manager.user.model.dto.UserDTO;
import com.projects.personal_task_manager.user.model.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DataTransformService {
    public TaskDTO toTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        Date dueDate = null;
        Status status = null;
        Category category = null;
        Long userId = null;

        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        dueDate = task.getDueDate();
        status = task.getStatus();
        category = task.getCategory();
        userId = task.getUser().getId();

        return new TaskDTO( id, title, description, dueDate, status, category, userId );
    }

    public Task toTask(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );
        task.setDueDate( taskDTO.getDueDate() );
        task.setStatus( taskDTO.getStatus() );
        task.setCategory( taskDTO.getCategory() );
        User user = new User();
        user.setId( taskDTO.getUserId() );
        task.setUser( user );

        return task;
    }

    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;
        String oldPassword = null;
        Role role = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        oldPassword = user.getPassword();
        role = (user.getRole() == null) ? Role.USER : user.getRole();

        return new UserDTO( id, username, email, oldPassword, null, role);
    }

    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setEmail( userDTO.getEmail() );
        user.setRole( userDTO.getRole() );

        return user;
    }
}
