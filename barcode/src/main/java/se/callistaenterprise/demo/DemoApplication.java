package se.callistaenterprise.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication { // extends SpringBootServletInitializer { // to support WAR deployment

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
