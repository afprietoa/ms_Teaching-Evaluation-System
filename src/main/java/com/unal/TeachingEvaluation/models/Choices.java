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
@Table(name = "choices")
public class Choices implements Serializable {
    @Id
    private Integer idOption;
    private String optionText;

    @ManyToMany
    @JoinTable(
            name = "questions_choices",
            joinColumns = @JoinColumn(name = "idOption"),
            inverseJoinColumns = @JoinColumn(name = "idQuestion" )
    )
    @JsonIgnoreProperties("choices")
    private List<Questions> questions;

}
