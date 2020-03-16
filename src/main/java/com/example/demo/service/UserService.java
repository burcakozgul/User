package com.example.demo.service;

import com.example.demo.exception.UserException;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.respository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public CreateUserResponse createPlayer(CreateUserRequest request) {
        User user = new User();
        CreateUserResponse createUserResponse = new CreateUserResponse();
        try {
           // if (userRepository.existsById(request.getId())) throw new UserException("User is already exist");
            if (userRepository.existsByemail(request.getEmail())) throw new UserException("This email is registered. Please different one.");
            else if (userRepository.existsByusername(request.getUsername())) throw new UserException("This username is registered. Please different one");
           // user.setId(request.getId());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setTckn(request.getTckn());
            user.setStatus(request.getStatus());
            user.setEmail(request.getEmail());
            createUserResponse.setResultCode(0);
            createUserResponse.setResultDesc("Success");
            userRepository.save(user);
        }
        catch (Exception e){
            createUserResponse.setResultCode(-1);
            createUserResponse.setResultDesc(e.getMessage());
        }
        return createUserResponse;
    }

    public GetUserResponse getUserResponse(){
        List<User> users= new ArrayList<>();
        GetUserResponse getUserResponse = new GetUserResponse();
        try {
            users=userRepository.findAll();
            getUserResponse.setUsers(users);
            getUserResponse.setResultCode(0);
            getUserResponse.setResultDesc("Success");
        }
        catch (Exception e){
            getUserResponse.setResultDesc(e.getMessage());
            getUserResponse.setResultCode(-1);
        }
        return getUserResponse;
    }

    public UpdateUserInfoResponse updateUser(Long id, UpdateUserInfoRequest request) throws UserException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found on : "+ id));
        UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
        try {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setStatus(request.getStatus());
            updateUserInfoResponse.setResultCode(0);
            updateUserInfoResponse.setResultDesc("Success");
            userRepository.save(user);
        }
        catch (Exception e){
            updateUserInfoResponse.setResultCode(-1);
            updateUserInfoResponse.setResultDesc(e.getMessage());
        }
        return updateUserInfoResponse;
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        LoginUserResponse response = new LoginUserResponse();
        try {
            if (userRepository.existsByusername(request.getUsername())) {
                User user = userRepository.getUserByusername(request.getUsername());
                if(passwordEncoder.matches(request.getPassword(),user.getPassword())) {
                    response.setResultCode(0);
                    response.setResultDesc("Success");
                }
                else {
                    response.setResultCode(-1);
                    response.setResultDesc("User not found");
                }
            }
            else {
                response.setResultCode(-1);
                response.setResultDesc("User not found");
            }

            } catch (Exception ex) {
            response.setResultCode(-1);
            response.setResultDesc(ex.getMessage());
        }
        return  response;
    }

}
