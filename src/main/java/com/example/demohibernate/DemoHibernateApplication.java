package com.example.demohibernate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class DemoHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHibernateApplication.class, args);
	}


	@Bean
	public ApplicationRunner applicationRunner(PersonRepository personRepository,
											   PersonCustomRepo personCustomRepo) {
		return args -> {
			Arrays.asList("Amar","Sachin")
					.forEach(name -> personRepository.save(new Person(name)));
			personRepository.findAll().forEach(person -> log.info("Person: {}", person));
			Person amar = personCustomRepo.findByName("Amar");
			log.info("Amar: {}", amar);

			Person sachin = personRepository.findByName("Sachin").get();
			log.info("Sachin: {}", sachin);
		};
	}
}
