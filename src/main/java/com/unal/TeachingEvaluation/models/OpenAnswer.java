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
@Table(name = "openAnswer")
public class OpenAnswer implements Serializable {
    @Id
    private Integer idOpenAnswer;
    private String answerText;

    @ManyToOne
    @JoinColumn(name="idQuestion")
    @JsonIgnoreProperties("openAnswer")
    private Questions question;

    @ManyToOne
    @JoinColumn(name="idStudent")
    @JsonIgnoreProperties("openAnswer")
    private Students student;
}
