package com.bank.loan;

import com.bank.loan.domain.model.User;
import com.bank.loan.infrastructure.persistence.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
public class LoanManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementApplication.class, args);


	}

	@Bean
    CommandLineRunner createAdmin(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {

			if (userRepository
					.findByUsername("admin")
					.isEmpty()) {

				User admin = new User(
						"admin",
						passwordEncoder.encode("admin123"),
						"ROLE_ADMIN"
				);

				userRepository.save(admin);
			}
		};
	}

}
