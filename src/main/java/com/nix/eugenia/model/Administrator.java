package com.nix.eugenia.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "administrator")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String login;
    @Column
    private String password;


}
