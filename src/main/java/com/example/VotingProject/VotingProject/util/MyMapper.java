package com.example.VotingProject.VotingProject.util;


import com.example.VotingProject.VotingProject.dto.UserDto;
import com.example.VotingProject.VotingProject.model.User;
import org.springframework.stereotype.Component;

@Component
public class MyMapper {

    public UserDto mapUserToUserDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

}
