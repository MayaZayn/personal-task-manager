package com.projects.personal_task_manager.user.model.dto;

import com.projects.personal_task_manager.user.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String oldPassword;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;
}
