package bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Account Service Application Entry Point
 * 
 * Best Practices Applied:
 * 1. @EnableJpaAuditing - Enables automatic population of @CreatedDate
 * and @LastModifiedDate
 * 2. Separate package structure for better organization
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
