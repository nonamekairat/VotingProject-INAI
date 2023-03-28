package com.example.VotingProject.VotingProject.controller;


import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.service.UserService;
import com.example.VotingProject.VotingProject.util.MyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final MyMapper mapper;

    @GetMapping("/")
    public String home(Principal principal, Model model){

        User user = userService.getUserByPrinciple(principal);
//        model.addAttribute("user", user);

        model.addAttribute("user", mapper.mapUserToUserDto(user));

        return "index";
    }
}
