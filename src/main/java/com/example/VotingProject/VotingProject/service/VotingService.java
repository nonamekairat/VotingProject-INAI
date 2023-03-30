package com.example.VotingProject.VotingProject.service;


import com.example.VotingProject.VotingProject.model.Candidate;
import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.model.Vote;
import com.example.VotingProject.VotingProject.model.Voting;
import com.example.VotingProject.VotingProject.model.enums.Status;
import com.example.VotingProject.VotingProject.repository.CandidateRepository;
import com.example.VotingProject.VotingProject.repository.VotingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VotingService {

    private final VotingRepository votingRepository;
    private final CandidateRepository candidateRepository;

    public Long createVoting(Voting voting, User user) {
        voting.setStatus(Status.ACTIVE);
        voting.setAuthor(user);
        Voting voting1 = votingRepository.save(voting);
        return voting1.getId();
    }

    public List<Voting> votingList(Status status){
        return votingRepository.findAll().stream()
                .filter(c -> c.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public Voting getVotingById(Long id) {
        return votingRepository.findById(id).orElseThrow();
    }

    public void addCandidate(Long id, String candidate) {

        Voting voting = votingRepository.findById(id).orElseThrow();
        List<Candidate> candidateList = voting.getCandidates();
        candidateList.add(candidateRepository.findByFirstName(candidate));
        voting.setCandidates(candidateList);


        votingRepository.save(voting);

    }

    public List<Candidate> getCandidatesByVotingId(Long id) {

        return votingRepository.findById(id).orElseThrow().getCandidates();
    }

    public Boolean isVoted(Voting voting, User user) {

        List<Vote> votes = voting.getVotes();


        for (Vote vote : votes) {
                if(Objects.equals(vote.getUser().getId(), user.getId())){
                    return true;
                }
        }


        return false;
    }

    public void addVote(Long id, User user, Long candidate_id) {
        Voting voting = votingRepository.findById(id).orElseThrow();
        List<Vote> votes = voting.getVotes();
        LocalDateTime now = LocalDateTime.now();

        if (convertToLocalDateTime(voting.getEndTime()).compareTo(now) < 0 ||
                voting.getStatus().equals(Status.FINISHED)) {
            throw new IllegalArgumentException("Голосование уже закончилось");
        }
        for (Vote vote : votes) {
            if (Objects.equals(vote.getUser().getId(), user.getId())) {
                System.out.println(vote.getUser().getId());
                System.out.println(user.getId());
                throw new IllegalArgumentException("пользователь уже проголосовал");
            }
        }
        
        votes.add(Vote.builder()
                        .candidate(candidateRepository.findById(candidate_id).orElseThrow())
                        .user(user)
//                        .creationTime(Date.now())
                .build());
        voting.setVotes(votes);

        votingRepository.save(voting);

    }

    public Object isAuthor(Voting voting, User user) {
        return Objects.equals(voting.getAuthor().getId(), user.getId());
    }

    public void finishVoting(Long id) {
        Voting voting = getVotingById(id);
        voting.setStatus(Status.FINISHED);
        votingRepository.save(voting);
    }
    public void finishVoting(Long id, User user) {

        Voting voting = getVotingById(id);

        if(Objects.equals(user.getId(), voting.getAuthor().getId())){
            throw new  IllegalArgumentException("Этот пользователь не может удалить данный опрос");
        }

        voting.setStatus(Status.FINISHED);
        votingRepository.save(voting);
    }



    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
