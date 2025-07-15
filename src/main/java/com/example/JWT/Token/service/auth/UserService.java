package com.example.JWT.Token.service.auth;

import com.example.JWT.Token.model.User;
import com.example.JWT.Token.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepository.findUserByEmail(username).orElse(null);
            if(user!=null){
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().toString())
                        .build();
            }else {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
