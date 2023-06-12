package com.unal.TeachingEvaluation.controllers;

import com.unal.TeachingEvaluation.models.Teachers;
import com.unal.TeachingEvaluation.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeachersController {
    @Autowired
    private TeachersService teacherService;

    @GetMapping("/all")
    public List<Teachers> getAllTeachers(){
        return this.teacherService.index();
    }

    @GetMapping("/by_id/{id}")
    public Optional<Teachers> getTeacherById(@PathVariable("id") int id){
        return this.teacherService.show(id);
    }

    @GetMapping("/by_name/{name}")
    public Optional<Teachers> getTeacherByName(@PathVariable("name") String name){
        return this.teacherService.showByName(name);
    }

    @GetMapping("/by_email/{email}")
    public Optional<Teachers> getTeacherByEmail(@PathVariable("email") String email){
        return this.teacherService.showByEmail(email);
    }

    @PostMapping("/insert")
    public Teachers insertTeacher(@RequestBody Teachers teacher){
        return this.teacherService.create(teacher);
    }

    @PutMapping("/update/{id}")
    public Teachers updateTeacher(@PathVariable("id") int id, @RequestBody Teachers teacher){
        return this.teacherService.update(id, teacher);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteTeacher(@PathVariable("id") int id){
        return this.teacherService.delete(id);
    }
}
