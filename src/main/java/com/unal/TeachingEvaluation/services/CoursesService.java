package com.unal.TeachingEvaluation.services;

import com.unal.TeachingEvaluation.models.Courses;
import com.unal.TeachingEvaluation.models.Students;
import com.unal.TeachingEvaluation.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class CoursesService {
    @Autowired
    private CoursesRepository courseRepository;

    /**
     *
     * @return
     */
    public List<Courses> index(){
        List<Courses> resultList = (List<Courses>) this.courseRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any course in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Courses> show(int id){
        Optional<Courses> result = this.courseRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested course.id does not exist.");
        return  result;
    }

    /**
     *
     * @param name
     * @return
     */
    public Optional<Courses> showByName(String name){
        Optional<Courses> result = this.courseRepository.findByName(name);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested course.name does not exists.");
        return  result;
    }

    /**
     *
     * @param newCourse
     * @return
     */
    public Courses create(Courses newCourse){
        if(newCourse.getIdCourse() != null){
            Optional<Courses> tempCourse = this.courseRepository.findById(newCourse.getIdCourse());
            if(tempCourse.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newCourse.getName() != null) && (newCourse.getCreditNumber() != null)){
            this.courseRepository.save(newCourse);
            return newCourse;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param course
     * @return
     */
    public  Courses update(int id, Courses course){
        if(id > 0){
            Optional<Courses> tempCourse = this.courseRepository.findById(id);
            if(tempCourse.isPresent()){
                if(course.getName() != null)
                    tempCourse.get().setName(course.getName());
                if (course.getCreditNumber() != null)
                    tempCourse.get().setCreditNumber(course.getCreditNumber());
                this.courseRepository.save(tempCourse.get());
                return tempCourse.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Course.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Course.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.courseRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be deleted.");
    }
}
