package com.example.VotingProject.VotingProject.service;


import com.example.VotingProject.VotingProject.dto.CandidatesWithVotes;
import com.example.VotingProject.VotingProject.model.Candidate;
import com.example.VotingProject.VotingProject.model.Voting;
import com.example.VotingProject.VotingProject.model.enums.Status;
import com.example.VotingProject.VotingProject.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;


    public List<Candidate> candidateList(){
        return candidateRepository.findAll();
    }

    public void save(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public List<CandidatesWithVotes> getCandidatesWithVotes(Voting voting) {

        List<CandidatesWithVotes> candidates = new ArrayList<>();
        for (int i = 0; i < voting.getCandidates().size(); i++) {
            System.out.println(voting.getCandidates().get(i).getFirstName());
            candidates.add(CandidatesWithVotes.builder()
                    .firstName(voting.getCandidates().get(i).getFirstName())
                    .lastName(voting.getCandidates().get(i).getLastName())
                    .id(voting.getCandidates().get(i).getId())
                    .voteScore(0L).build()

            );
        }

        for (int i = 0; i < voting.getVotes().size(); i++) {
            for (CandidatesWithVotes candidate : candidates) {
                if (Objects.equals(voting.getVotes().get(i).getCandidate().getId(), candidate.getId())) {
                    candidate.setVoteScore(candidate.getVoteScore() + 1);
                };
            }
        }
        candidates.sort((o1, o2) -> {
            if (Objects.equals(o1.getVoteScore(), o2.getVoteScore())) return 0;
            if (o1.getVoteScore() > o2.getVoteScore())
                return -1;
            else return 1;
        });


        return candidates;
    }
}
