package com.example.demo.controller;

import com.example.demo.exception.UserException;
import com.example.demo.model.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.example.demo.respository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CreateUserResponse createPlayer(@Valid @RequestBody CreateUserRequest request) {
        return userService.createPlayer(request);
    }

    @GetMapping("/getusers")
    public GetUserResponse getUsers() {
        return userService.getUserResponse();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id")Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public UpdateUserInfoResponse updateUser(@PathVariable("id")Long id,@Valid @RequestBody UpdateUserInfoRequest request) throws UserException {
        return userService.updateUser(id,request);
    }

    @PostMapping("/login")
    public LoginUserResponse loginUser(@Valid @RequestBody LoginUserRequest request){
        return userService.loginUser(request);
    }
}
