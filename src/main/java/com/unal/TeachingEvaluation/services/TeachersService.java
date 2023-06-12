package com.unal.TeachingEvaluation.services;

import com.unal.TeachingEvaluation.models.Teachers;
import com.unal.TeachingEvaluation.repositories.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class TeachersService {
    @Autowired
    private TeachersRepository teacherRepository;

    /**
     *
     * @return
     */
    public List<Teachers> index(){
        List<Teachers> resultList = (List<Teachers>) this.teacherRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any teacher in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Teachers> show(int id){
        Optional<Teachers> result = this.teacherRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested teacher.id does not exist.");
        return  result;
    }

    /**
     *
     * @param name
     * @return
     */
    public Optional<Teachers> showByName(String name){
        Optional<Teachers> result = this.teacherRepository.findByName(name);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested teacher.name does not exists.");
        return  result;
    }

    /**
     *
     * @param email
     * @return
     */
    public Optional<Teachers> showByEmail(String email){
        Optional<Teachers> result = this.teacherRepository.findByEmail(email);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested teacher.email does not exists.");
        return  result;
    }

    /**
     *
     * @param newTeacher
     * @return
     */
    public Teachers create(Teachers newTeacher){
        if(newTeacher.getIdTeacher() != null){
            Optional<Teachers> tempTeacher = this. teacherRepository.findById(newTeacher.getIdTeacher());
            if(tempTeacher.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newTeacher.getName() != null) && (newTeacher.getEmail() != null) &&
                (newTeacher.getDedicationTime() != null) && (newTeacher.getAcademicLevel() != null) &&
                (newTeacher.getDepartment() != null) && (newTeacher.getActiveStatus() != null)){
            this.teacherRepository.save(newTeacher);
            return newTeacher;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param teacher
     * @return
     */
    public  Teachers update(int id, Teachers teacher){
        if(id > 0){
            Optional<Teachers> tempTeacher = this.teacherRepository.findById(id);
            if(tempTeacher.isPresent()){
                if(teacher.getName() != null)
                    tempTeacher.get().setName(teacher.getName());
                if (teacher.getEmail() != null)
                    tempTeacher.get().setEmail(teacher.getEmail());
                if(teacher.getDedicationTime() != null)
                    tempTeacher.get().setDedicationTime(teacher.getDedicationTime());
                if(teacher.getAcademicLevel() != null)
                    tempTeacher.get().setAcademicLevel(teacher.getAcademicLevel());
                if(teacher.getDepartment() != null)
                    tempTeacher.get().setDepartment(teacher.getDepartment());
                if(teacher.getActiveStatus() != null)
                    tempTeacher.get().setActiveStatus(teacher.getActiveStatus());
                this.teacherRepository.save(tempTeacher.get());
                return tempTeacher.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Teacher.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Teacher.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.teacherRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be deleted.");
    }

}
