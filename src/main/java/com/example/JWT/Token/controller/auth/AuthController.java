package com.example.JWT.Token.controller.auth;

import com.example.JWT.Token.dto.userProfile.LoginResponseDTO;
import com.example.JWT.Token.model.User;
import com.example.JWT.Token.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home() {
        return "Server is running!";
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody User user){
       return authService.login(user);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        return authService.register(user);
    }
    @GetMapping("/hello")
    public String sayHello(){
        return "Authorized";
    }
}
