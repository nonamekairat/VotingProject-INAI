package com.example.VotingProject.VotingProject.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum Status {
    ACTIVE("активный"), FINISHED("законченные");

    private final String status;

    public static Status of(String status) {
        return Stream.of(Status.values())
                .filter(p -> p.getStatus().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
