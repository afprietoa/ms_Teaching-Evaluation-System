package com.unal.TeachingEvaluation.controllers;

import com.unal.TeachingEvaluation.models.Students;
import com.unal.TeachingEvaluation.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentsController {
    @Autowired
    private StudentsService studentService;

    @GetMapping("/all")
    public List<Students> getAllStudents(){
        return this.studentService.index();
    }

    @GetMapping("/by_id/{id}")
    public Optional<Students> getStudentById(@PathVariable("id") int id){
        return this.studentService.show(id);
    }

    @GetMapping("/by_name/{name}")
    public Optional<Students> getStudentByName(@PathVariable("name") String name){
        return this.studentService.showByName(name);
    }

    @GetMapping("/by_email/{email}")
    public Optional<Students> getStudentByEmail(@PathVariable("email") String email){
        return this.studentService.showByEmail(email);
    }

    @PostMapping("/insert")
    public Students insertStudent(@RequestBody Students student){
        return this.studentService.create(student);
    }

    @PutMapping("/update/{id}")
    public Students updateStudent(@PathVariable("id") int id, @RequestBody Students student){
        return this.studentService.update(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteStudent(@PathVariable("id") int id){
        return this.studentService.delete(id);
    }
}
