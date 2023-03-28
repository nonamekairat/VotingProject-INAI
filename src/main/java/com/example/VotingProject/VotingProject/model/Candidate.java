package com.example.VotingProject.VotingProject.model;


import com.example.VotingProject.VotingProject.model.baseEntity.BaseEntity;
import com.example.VotingProject.VotingProject.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Candidate extends BaseEntity {


    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;



}
