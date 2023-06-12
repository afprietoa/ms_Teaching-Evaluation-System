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
    private Integer idTeacher;
    private String name;
    private String email;
    private Integer dedicationTime;
    private String academicLevel;
    private String department;
    private String activeStatus;

    @OneToOne(mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private Users user;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private List<Evaluation> evaluations;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "teachers")
    @JsonIgnoreProperties("teachers")
    private List<Courses> courses;

}
