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
@Table(name = "teachers")
public class Teachers implements Serializable {
    @Id
    @Column(name = "idTeacher")
    private Integer idTeacher;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "dedicationTime")
    private Integer dedicationTime;
    @Column(name = "academicLevel")
    private String academicLevel;
    @Column(name = "department")
    private String department;
    @Column(name = "activeStatus")
    private String activeStatus;

    @OneToOne(mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private Users users;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private List<Evaluation> evaluations;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private List<Courses> courses;

}
