package com.unal.TeachingEvaluation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Students implements Serializable {
    @Id
    private Integer idStudent;
    private String name;
    private String email;
    private String gender;
    private String curricularProgram;
    private String department;
    private String activeStatus;
    private String evalBool;

    @OneToOne(mappedBy = "students")
    @JsonIgnoreProperties("students")
    private Users user;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "students")
    @JsonIgnoreProperties("students")
    private List<OpenAnswer> openAnswers;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "students")
    @JsonIgnoreProperties("students")
    private List<ClosedAnswer> closedAnswers;
}
