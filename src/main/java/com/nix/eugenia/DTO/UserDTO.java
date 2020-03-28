package com.nix.eugenia.DTO;

import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private Date startTime;
    private List<RoleDTO> roles;
}
