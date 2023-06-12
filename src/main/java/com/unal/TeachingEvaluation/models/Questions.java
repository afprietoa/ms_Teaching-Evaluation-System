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
@Table(name = "questions")
public class Questions implements Serializable {
    @Id
    @Column(name = "idQuestion")
    private Integer idQuestion;
    @Column(name = "question")
    private String questionText;
    @Column(name = "category")
    private String category;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "questions")
    @JsonIgnoreProperties("questions")
    private List<OpenAnswer> openAnswers;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnoreProperties("questions")
    private List<Evaluation> evaluations;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnoreProperties("questions")
    private List<Choices> choices;

}
