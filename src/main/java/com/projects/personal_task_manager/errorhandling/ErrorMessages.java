package com.projects.personal_task_manager.errorhandling;

public interface ErrorMessages {
    String USER_NOT_FOUND = "This user doesn't exist.";
    String TASK_NOT_FOUND = "This task doesn't exist.";

    String INVALID_CREDENTIALS = "Username or password is incorrect.";
    String INVALID_USERNAME = "Username must be at least 3 characters long.";
    String INVALID_PASSWORD = "Password must be at least 8 characters long.";
    String INVALID_EMAIL = "Invalid email format.";
    String INVALID_OLD_PASSWORD = "Old password is incorrect.";

    String ACCESS_DENIED_TO_TASK = "You are only allowed to access tasks from your task list.";
    String ACCESS_DENIED_TO_USER = "You are allowed to access your info only.";

    String OLD_PASSWORD_REQUIRED = "Old password is not provided.\n" +
            "You need to enter your old password to update your personal information.";
    String TITLE_REQUIRED = "Task title is required.";
    String CREDENTIALS_REQUIRED = "Username or password cannot be empty.";

    String USER_ALREADY_EXISTS = "This user already exists.";

    String UNEXPECTED_ERROR = "Unexpected error.";
}
