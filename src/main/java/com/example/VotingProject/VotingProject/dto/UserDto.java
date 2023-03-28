package com.example.VotingProject.VotingProject.dto;

import com.example.VotingProject.VotingProject.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    String username;

    String firstName;

    String lastName;



}
