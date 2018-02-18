package api.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootAPI {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAPI.class, args);
		
		System.out.println("Spring Boot API started.");
	}

}
