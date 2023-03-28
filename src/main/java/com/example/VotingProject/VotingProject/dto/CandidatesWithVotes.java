package com.example.VotingProject.VotingProject.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidatesWithVotes {

    Long id;

    String firstName;

    String lastName;

    Long voteScore;

}
