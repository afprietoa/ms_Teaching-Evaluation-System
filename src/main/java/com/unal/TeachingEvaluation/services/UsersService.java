package com.unal.TeachingEvaluation.services;

import com.unal.TeachingEvaluation.models.Users;
import com.unal.TeachingEvaluation.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository userRepository;

    /**
     *
     * @return
     */
    public List<Users> index(){
        List<Users> resultList = (List<Users>) this.userRepository.findAll();
        if (resultList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There is not any user in the list.");
        return  resultList;

    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Users> show(int id){
        Optional<Users> result = this.userRepository.findById(id);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested user.id does not exist.");
        return  result;
    }

    /**
     *
     * @param role
     * @return
     */
    public Optional<Users> showByRole(String role){
        Optional<Users> result = this.userRepository.findByRole(role);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested user.role does not exists.");
        return  result;
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<Users> showByNickname(String username){
        Optional<Users> result = this.userRepository.findByUsername(username);
        if(result.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The requested user.nickname does not exists.");
        return  result;
    }

    /**
     *
     * @param newUser
     * @return
     */
    public Users create(Users newUser){
        if(newUser.getIdUser() != null){
            Optional<Users> tempUser = this. userRepository.findById(newUser.getIdUser());
            if(tempUser.isPresent())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "ID is yet in the database.");
        }

        if((newUser.getRole() != null) && (newUser.getUsername() != null) &&
                (newUser.getPassword() != null)){
            newUser.setPassword(this.convertToSHA256(newUser.getPassword()));
            this.userRepository.save(newUser);
            return newUser;
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    public  Users update(int id, Users user){
        if(id > 0){
            Optional<Users> tempUser = this.userRepository.findById(id);
            if(tempUser.isPresent()){
                if(user.getUsername() != null)
                    tempUser.get().setUsername(user.getUsername());
                if (user.getPassword() != null)
                    tempUser.get().setPassword(this.convertToSHA256(user.getPassword()));
                if(user.getRole() != null)
                    tempUser.get().setRole(user.getRole());
                this.userRepository.save(tempUser.get());
                return tempUser.get();
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User.id does not exist in database.");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User.id cannot be negative.");
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Boolean delete(int id) {
        Boolean success = this.show(id).map( user -> {
            this.userRepository.delete(user);
            return true;
        }).orElse(false);
        if(success)
            return true;
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be deleted.");
    }

    public Users login(Users user){
        Users result;
        if((user.getPassword() != null) && (user.getUsername() != null)){
            String email = user.getUsername();
            String password = this.convertToSHA256(user.getPassword());
            Optional<Users> tempUser = this.userRepository.validateLogin(email, password);
            if(tempUser.isEmpty())
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Invalid login.");
            else
                result = tempUser.get();
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mandatory fields had not been provided.");
        return result;
    }


    /**
     *
     * @param password
     * @return
     */
    public String convertToSHA256(String password){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        StringBuffer sb = new StringBuffer();
        byte[] hash = md.digest(password.getBytes());
        for(byte b: hash)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
