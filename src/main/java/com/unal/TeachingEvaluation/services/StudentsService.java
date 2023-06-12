package com.unal.TeachingEvaluation.services;

import com.unal.TeachingEvaluation.models.Students;
import com.unal.TeachingEvaluation.models.Teachers;
import com.unal.TeachingEvaluation.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class StudentsService {
    @Autowired
    private StudentsRepository studentRepository;

    /**
     *
     * @return
     */
    public List<Students> index(){
        List<Students> resultList = (List<Students>) this.studentRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any student in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Students> show(int id){
        Optional<Students> result = this.studentRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested student.id does not exist.");
        return  result;
    }

    /**
     *
     * @param name
     * @return
     */
    public Optional<Students> showByName(String name){
        Optional<Students> result = this.studentRepository.findByName(name);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested student.name does not exists.");
        return  result;
    }

    /**
     *
     * @param email
     * @return
     */
    public Optional<Students> showByEmail(String email){
        Optional<Students> result = this.studentRepository.findByEmail(email);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested student.email does not exists.");
        return  result;
    }

    /**
     *
     * @param newStudent
     * @return
     */
    public Students create(Students newStudent){
        if(newStudent.getIdStudent() != null){
            Optional<Students> tempStudent = this.studentRepository.findById(newStudent.getIdStudent());
            if(tempStudent.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newStudent.getName() != null) && (newStudent.getEmail() != null) &&
                (newStudent.getGender() != null) && (newStudent.getCurricularProgram() != null) &&
                (newStudent.getDepartment() != null) && (newStudent.getActiveStatus() != null)
                && (newStudent.getEvalBool() != null)){
            this.studentRepository.save(newStudent);
            return newStudent;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param student
     * @return
     */
    public  Students update(int id, Students student){
        if(id > 0){
            Optional<Students> tempStudent = this.studentRepository.findById(id);
            if(tempStudent.isPresent()){
                if(student.getName() != null)
                    tempStudent.get().setName(student.getName());
                if (student.getEmail() != null)
                    tempStudent.get().setEmail(student.getEmail());
                if(student.getGender() != null)
                    tempStudent.get().setGender(student.getGender());
                if(student.getCurricularProgram() != null)
                    tempStudent.get().setCurricularProgram(student.getCurricularProgram());
                if(student.getDepartment() != null)
                    tempStudent.get().setDepartment(student.getDepartment());
                if(student.getActiveStatus() != null)
                    tempStudent.get().setActiveStatus(student.getActiveStatus());
                this.studentRepository.save(tempStudent.get());
                return tempStudent.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Student.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.studentRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be deleted.");
    }

}
