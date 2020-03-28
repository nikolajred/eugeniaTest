package com.nix.eugenia.DTO;

import com.nix.eugenia.model.Student;
import com.nix.eugenia.model.Teacher;
import com.nix.eugenia.security.entity.Role;
import com.nix.eugenia.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class UpdateEntity {
    private Student student;

    private Teacher teacher;
    private Role role;
    private User user;

}
