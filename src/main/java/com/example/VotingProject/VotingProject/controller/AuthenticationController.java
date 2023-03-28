package com.example.VotingProject.VotingProject.controller;


import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping("/authenticate")
    public String authenticate(Principal principal, Model model, @ModelAttribute("message") String message){
        model.addAttribute("user", userService.getUserByPrinciple(principal));
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute("user") User user){
        userService.register(user);
        return "redirect:/login";
    };



}
