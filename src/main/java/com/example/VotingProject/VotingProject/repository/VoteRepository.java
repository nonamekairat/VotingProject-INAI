package com.example.VotingProject.VotingProject.repository;

import com.example.VotingProject.VotingProject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
