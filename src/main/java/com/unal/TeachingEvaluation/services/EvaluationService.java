package com.unal.TeachingEvaluation.services;

import com.unal.TeachingEvaluation.models.Evaluation;
import com.unal.TeachingEvaluation.models.Students;
import com.unal.TeachingEvaluation.repositories.EvaluationRepository;
import com.unal.TeachingEvaluation.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    /**
     *
     * @return
     */
    public List<Evaluation> index(){
        List<Evaluation> resultList = (List<Evaluation>) this.evaluationRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any evaluation in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Evaluation> show(int id){
        Optional<Evaluation> result = this.evaluationRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested evaluation.id does not exist.");
        return  result;
    }

    /**
     *
     * @param date
     * @return
     */
    public Optional<Evaluation> showByDate(String date){
        Optional<Evaluation> result = this.evaluationRepository.findByDate(date);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested evaluation.date does not exists.");
        return  result;
    }

    /**
     *
     * @param semester
     * @return
     */
    public Optional<Evaluation> showBySemester(String semester){
        Optional<Evaluation> result = this.evaluationRepository.findBySemester(semester);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested evaluation.date does not exists.");
        return  result;
    }



    /**
     *
     * @param newEvaluation
     * @return
     */
    public Evaluation create(Evaluation newEvaluation){
        if(newEvaluation.getIdEvaluation() != null){
            Optional<Evaluation> tempEvaluation = this.evaluationRepository.findById(newEvaluation.getIdEvaluation());
            if(tempEvaluation.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newEvaluation.getTimeStamp() != null) && (newEvaluation.getSemester() != null)){
            this.evaluationRepository.save(newEvaluation);
            return newEvaluation;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param evaluation
     * @return
     */
    public  Evaluation update(int id, Evaluation evaluation){
        if(id > 0){
            Optional<Evaluation> tempEvaluation = this.evaluationRepository.findById(id);
            if(tempEvaluation.isPresent()){
                if(evaluation.getTimeStamp() != null)
                    tempEvaluation.get().setTimeStamp(evaluation.getTimeStamp());
                if (evaluation.getSemester() != null)
                    tempEvaluation.get().setSemester(evaluation.getSemester());
                this.evaluationRepository.save(tempEvaluation.get());
                return tempEvaluation.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Evaluation.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Evaluation.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.evaluationRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be deleted.");
    }
}
