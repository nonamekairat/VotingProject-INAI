package com.example.VotingProject.VotingProject.repository;

import com.example.VotingProject.VotingProject.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {


    Candidate findByFirstName(String candidateFistName);

}
