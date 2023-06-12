package com.unal.TeachingEvaluation.controllers;

import com.unal.TeachingEvaluation.models.Evaluation;
import com.unal.TeachingEvaluation.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/all")
    public List<Evaluation> getAllEvaluations(){
        return this.evaluationService.index();
    }

    @GetMapping("/by_id/{id}")
    public Optional<Evaluation> getEvaluationById(@PathVariable("id") int id){
        return this.evaluationService.show(id);
    }

    @GetMapping("/by_date/{date}")
    public Optional<Evaluation> getEvaluationByDate(@PathVariable("evaluation") String date){
        return this.evaluationService.showByDate(date);
    }

    @GetMapping("/by_semester/{semester}")
    public Optional<Evaluation> getEvaluationBySemester(@PathVariable("semester") String semester){
        return this.evaluationService.showBySemester(semester);
    }

    @PostMapping("/insert")
    public Evaluation insertEvaluation(@RequestBody Evaluation evaluation){
        return this.evaluationService.create(evaluation);
    }

    @PutMapping("/update/{id}")
    public Evaluation updateEvaluation(@PathVariable("id") int id, @RequestBody Evaluation evaluation){
        return this.evaluationService.update(id, evaluation);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteEvaluation(@PathVariable("id") int id){
        return this.evaluationService.delete(id);
    }
}
