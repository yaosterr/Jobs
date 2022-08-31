package com.SalJobs.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.SalJobs.backend.model.User;
import com.SalJobs.backend.repository.UserRepository;

/*
 * API methods go here. @PostMapping means it's a post method, @GetMapping means
 * get method. etc. etc. 
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signIn")
    public String signIn(@RequestBody User user){
    	User target = userRepository.findByEmail(user.getEmail());
    	if(target==null) {
    		return "Email not found";
    	}
    	if(!target.getPassword().equals(user.getPassword())) {
    		return "Incorrect Password";
    	}
        return Long.toString(target.getId())+"%"+target.getName();
    }

    // build create employee REST API
    @PostMapping("/register")
    public String register(@RequestBody User user){
    	User newUser = new User(0,user.getEmail(),user.getName(),user.getPassword());
    	userRepository.save(newUser);
        return Long.toString(newUser.getId())+"%"+newUser.getName();
    }
    

}
