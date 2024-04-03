package com.nicolas.cepSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class CepSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepSpringApplication.class, args);
	}

	//O primeiro "0" é os segundos
	//O segundo "0" é os minutos
	//O terceiro "0" é as horas
	@Scheduled(cron = "0 40 15 * * *")
	public void executeTask1(){
		System.out.println("Funcionou1");
	}

}
