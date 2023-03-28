package com.example.VotingProject.VotingProject.model;


import com.example.VotingProject.VotingProject.model.baseEntity.BaseEntity;
import com.example.VotingProject.VotingProject.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "voting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voting extends BaseEntity {

    @Column(name = "vote_name")
    String name;


    @JoinColumn(name = "candidate_id")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "candidate_voting_item",
    joinColumns = @JoinColumn(name = "voting_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Candidate> candidates;

    @JoinColumn(name = "vote_id")
    @OneToMany(cascade = CascadeType.ALL)
    List<Vote> votes;


    @Column(name = "rec_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date creationTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endTime;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    User author;





}
