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
@Table(name = "closedAnswer")
public class ClosedAnswer implements Serializable {
    @Id
    @Column(name = "idClosedAnswer")
    private Integer idClosedAnswer;

    @ManyToOne
    @JoinColumn(name="idStudent")
    @JsonIgnoreProperties("closedAnswer")
    private Students student;

    @ManyToOne
    @JoinColumn(name="idOption")
    @JsonIgnoreProperties("closedAnswer")
    private Choices choice;
}
