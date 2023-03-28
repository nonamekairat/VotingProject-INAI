package com.example.VotingProject.VotingProject.controller;


import com.example.VotingProject.VotingProject.dto.CandidatesWithVotes;
import com.example.VotingProject.VotingProject.model.Candidate;
import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.model.Voting;
import com.example.VotingProject.VotingProject.model.enums.Status;
import com.example.VotingProject.VotingProject.service.CandidateService;
import com.example.VotingProject.VotingProject.service.UserService;
import com.example.VotingProject.VotingProject.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;
    private final CandidateService candidateService;
    private final UserService userService;

    @GetMapping("/all")
    public String votingPage(Model model, Principal principal){

        model.addAttribute(model.addAttribute("votings", votingService.votingList(Status.ACTIVE)));
        model.addAttribute(model.addAttribute("user", userService.getUserByPrinciple(principal)));

        return "voting/votings";
    }

    @GetMapping("/all/finished")
    public String votingfinishedPage(Model model, Principal principal){

        model.addAttribute(model.addAttribute("votings", votingService.votingList(Status.FINISHED)));
        model.addAttribute(model.addAttribute("user", userService.getUserByPrinciple(principal)));

        return "voting/votings";
    }

    @GetMapping("/{id}/finished")
    public String finishedVote(
            @PathVariable Long id,
            Model model,
            Principal principal){

        Voting voting = votingService.getVotingById(id);
        List<CandidatesWithVotes> candidates = candidateService.getCandidatesWithVotes(voting);
        System.out.println(candidates);
        User user = userService.getUserByPrinciple(principal);

        model.addAttribute("candidates", candidates);
        model.addAttribute("voting", voting);
        model.addAttribute("user", user);
        return "voting/voting-finished";

    }

    @PostMapping("/{id}/finish")
    public String finishVoting(@PathVariable Long id){
        votingService.finishVoting(id);

        return "redirect:/voting/all/finished";
    }

    @GetMapping("/{id}")
    public String votingPage( @PathVariable Long id, Model model, Principal principal){

        Voting voting = votingService.getVotingById(id);
        User user = userService.getUserByPrinciple(principal);

        model.addAttribute("voting", voting);
        model.addAttribute("user", user);
        model.addAttribute("isVoted", votingService.isVoted(voting, user));
        model.addAttribute("isAuthor", votingService.isAuthor(voting, user));

        if(voting.getStatus().equals(Status.ACTIVE)) return "voting/voting";
        else return "redirect:/voting/" + voting.getId() + "/finished";
    }

    @GetMapping("/create")
    public String createVotingPage(@ModelAttribute("voting") Voting voting, Model model, Principal principal){

        model.addAttribute("user", userService.getUserByPrinciple(principal));
        model.addAttribute("voting", new Voting());
        return "voting/create_voting";
    }

    @GetMapping("/create/{id}/add-candidates")
    public String createCandidates(Model model, @PathVariable Long id){

        model.addAttribute("candidates", candidateService.candidateList());
        model.addAttribute("newCandidate", new Candidate());
        model.addAttribute("voting", votingService.getVotingById(id));
        model.addAttribute("voting_candidates", votingService.getCandidatesByVotingId(id));
        return "voting/add_candidates";
    }

    @PostMapping("/create/{id}/add-candidates")
    public String addCandidates(@RequestParam("candidate-add") String candidate, @PathVariable Long id){

        votingService.addCandidate(id, candidate);

        return "redirect:/voting/create/" + id + "/add-candidates";
    }

    @PostMapping("/{id}/vote/{candidate-id}")
    public String addCandidates(
            @PathVariable Long id,
            @ModelAttribute("user") User user,
            @PathVariable("candidate-id") Long candidateId){

        votingService.addVote(id, user, candidateId);

        return "redirect:/voting/{id}";
    }

    @PostMapping("/create")
    public String createVote(@ModelAttribute("user") User user,@ModelAttribute("voting") Voting voting){
        Long id = votingService.createVoting(voting, user);

        return "redirect:/voting/create/" + id + "/add-candidates";
    }

}
