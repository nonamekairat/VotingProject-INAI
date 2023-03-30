package com.example.VotingProject.VotingProject.model;

import com.example.VotingProject.VotingProject.model.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "vote")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vote extends BaseEntity {

    @JoinColumn(name = "candidate_id")
    @ManyToOne
    Candidate candidate;


    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @Column(name = "rec_time")
    @CreationTimestamp
    Date creationTime;

}
