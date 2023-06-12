package com.unal.TeachingEvaluation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    private Integer idUser;
    private String username;
    private String password;
    private String role;

    @OneToOne(mappedBy = "users")
    @JsonIgnoreProperties("users")
    private Students student;

    @OneToOne(mappedBy = "users")
    @JsonIgnoreProperties("users")
    private Teachers teacher;


}
