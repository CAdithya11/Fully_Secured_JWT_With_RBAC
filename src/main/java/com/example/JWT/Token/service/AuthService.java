package com.example.JWT.Token.service;

import com.example.JWT.Token.model.User;
import com.example.JWT.Token.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public String login(User user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
        );
        return "user have been successfully  logged in";
    }

    public String register(User user){
        String Encoded_pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(Encoded_pwd);
        userRepository.save(user);
        return "You have successfully registered";
    }
}
