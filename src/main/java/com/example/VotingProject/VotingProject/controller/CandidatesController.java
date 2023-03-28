package com.example.VotingProject.VotingProject.controller;


import com.example.VotingProject.VotingProject.model.Candidate;
import com.example.VotingProject.VotingProject.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/candidate")
public class CandidatesController {

    private final CandidateService candidateService;

    @PostMapping("/create/{voting_id}")
    public String createCandidate(@ModelAttribute("newCandidate") Candidate candidate,
                                  @PathVariable Long voting_id){
        candidateService.save(candidate);
        return "redirect:/voting/create/" + voting_id + "/add-candidates";
    }
}
