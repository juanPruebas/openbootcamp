package com.TrionfiniJuan.demo;

import com.TrionfiniJuan.demo.entities.Laptop;
import com.TrionfiniJuan.demo.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

//		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
//
//		// Como no vimos Mock creo unos datos para JUnit
//		laptopRepository.save( new Laptop( "Modelo 1", LocalDate.now() ));
//		laptopRepository.save( new Laptop( "Modelo 2", LocalDate.now() ));
	}

}
