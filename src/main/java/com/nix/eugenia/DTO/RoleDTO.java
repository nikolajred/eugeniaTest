package com.nix.eugenia.DTO;

import lombok.*;

import javax.persistence.Column;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;

}
