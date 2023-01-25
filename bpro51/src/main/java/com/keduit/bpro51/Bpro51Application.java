package com.keduit.bpro51;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing   // 만들어둔 baseEntity 넣는것
public class Bpro51Application {

	public static void main(String[] args) {
		SpringApplication.run(Bpro51Application.class, args);
	}

}
