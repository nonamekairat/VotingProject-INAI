package com.example.VotingProject.VotingProject;

import com.example.VotingProject.VotingProject.model.User;
import com.example.VotingProject.VotingProject.model.enums.Role;
import com.example.VotingProject.VotingProject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableScheduling
public class VotingProjectApplication {



//	private static final Logger log = LoggerFactory.getLogger(VotingProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VotingProjectApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(UserRepository userRepository) {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		return (args) -> {
//			userRepository.save(User.builder()
//							.firstName("Kairat")
//							.lastName("Abylgaziev")
//							.password(passwordEncoder.encode("1"))
//							.username("kairat")
//							.role(Role.ROLE_ADMIN)
//					.build());
//		};
//	}


}

