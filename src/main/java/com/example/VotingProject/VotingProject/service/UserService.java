package com.example.VotingProject.VotingProject.service;

import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.model.enums.Role;
import com.example.VotingProject.VotingProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByPrinciple(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByUsername(principal.getName()).orElse(new User());
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
    }
}
