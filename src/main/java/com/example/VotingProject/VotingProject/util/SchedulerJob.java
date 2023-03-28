package com.example.VotingProject.VotingProject.util;


import com.example.VotingProject.VotingProject.model.Voting;
import com.example.VotingProject.VotingProject.model.enums.Status;
import com.example.VotingProject.VotingProject.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SchedulerJob {

    private final VotingService votingService;

    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void checkAvailability(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        List<Voting> votingList = votingService.votingList(Status.ACTIVE);
        for (Voting voting : votingList) {
            System.out.println(convertToLocalDateTime(voting.getEndTime()).compareTo(now) < 0);
            if (convertToLocalDateTime(voting.getEndTime()).compareTo(now) < 0) {
                votingService.finishVoting(voting.getId());

            }
        }

    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
