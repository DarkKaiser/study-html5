package kr.co.darkkaiser.annotationConfigApplicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao createUserDao() {
		return new UserDao();
	}
	
}
