package com.example.JWT.Token.service.auth;

import com.example.JWT.Token.dto.userProfile.LoginResponseDTO;
import com.example.JWT.Token.enums.UserRole;
import com.example.JWT.Token.model.User;
import com.example.JWT.Token.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    public LoginResponseDTO login(User user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
        );
        User existingUser = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("user_id",user.getUser_id());
        claims.put("role",existingUser.getRole().toString());
        String token = jwtService.getJWTToken(user.getEmail(),claims);
        return new LoginResponseDTO(token,LocalDateTime.now(),null,"Success");
    }

    public String register(User user){
        String Encoded_pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(Encoded_pwd);
        user.setRole(UserRole.GUEST);
        userRepository.save(user);
        return "You have successfully registered";
    }
}
