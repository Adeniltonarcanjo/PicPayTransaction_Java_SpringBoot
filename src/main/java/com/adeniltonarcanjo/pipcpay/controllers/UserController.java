package com.adeniltonarcanjo.pipcpay.controllers;

import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.dtos.UserDTO;
import com.adeniltonarcanjo.pipcpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser= userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users= this.userService.getAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user= this.userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping(value = "documment/{documment}")
    public ResponseEntity<User> findUserByDocument(@PathVariable String documment){
        User user = this.userService.findUserByDocumment(documment);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();

    }



}
