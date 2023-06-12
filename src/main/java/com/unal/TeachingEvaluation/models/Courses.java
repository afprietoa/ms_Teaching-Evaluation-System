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
@Table(name = "courses")
public class Courses implements Serializable {
    @Id
    private Integer idCourse;
    private String name;
    private Integer creditNumber;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private List<Students> students;

    @ManyToOne
    @JoinColumn(name="idTeacher")
    @JsonIgnoreProperties("courses")
    private Teachers teacher;
}
