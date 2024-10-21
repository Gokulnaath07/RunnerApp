package dev.gokul.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Runnerz {
	private static final Logger log = LoggerFactory.getLogger(Runnerz.class);
	

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Runnerz.class, args);



	}
//	@Bean
//	CommandLineRunner runner(RunRepository runRepository) {
//		return args -> {
//			Run run = new Run(1, "Running", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1,Location.OUTDOOR);
//			runRepository.create(run);
//		};
//	}

}
