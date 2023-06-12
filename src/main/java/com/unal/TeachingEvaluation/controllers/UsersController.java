package com.unal.TeachingEvaluation.controllers;

import com.unal.TeachingEvaluation.models.Users;
import com.unal.TeachingEvaluation.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService userService;

    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return this.userService.index();
    }

    @GetMapping("/by_id/{id}")
    public Optional<Users> getUserById(@PathVariable("id") int id){
        return this.userService.show(id);
    }

    @GetMapping("/by_role/{role}")
    public Optional<Users> getUserByRole(@PathVariable("role") String role){
        return this.userService.showByRole(role);
    }

    @GetMapping("/by_nickname/{username}")
    public Optional<Users> getUserByUsername(@PathVariable("username") String username){
        return this.userService.showByNickname(username);
    }

    @PostMapping("/insert")
    public Users insertUser(@RequestBody Users user){
        return this.userService.create(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Users loginUser(@RequestBody Users user){
        return this.userService.login(user);
    }

    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable("id") int id, @RequestBody Users user){
        return this.userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable("id") int id){
        return this.userService.delete(id);
    }
}