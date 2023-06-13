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
    @Column(name = "idStudent")
    private Integer idStudent;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "curricularProgram")
    private String curricularProgram;
    @Column(name = "department")
    private String department;
    @Column(name = "activeStatus")
    private String activeStatus;
    @Column(name = "evalBool")
    private String evalBool;

    @OneToOne(mappedBy = "students")
    @JsonIgnoreProperties("students")
    private Users users;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "students")
    @JsonIgnoreProperties("students")
    private List<OpenAnswer> openAnswers;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "students")
    @JsonIgnoreProperties("students")
    private List<ClosedAnswer> closedAnswers;

    @ManyToOne
    @JoinColumn(name="idCourse")
    @JsonIgnoreProperties("students")
    private Courses courses;
}
