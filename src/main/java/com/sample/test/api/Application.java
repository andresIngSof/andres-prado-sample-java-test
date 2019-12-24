package com.sample.test.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : Application.java
 * Clase para inicializar aplicación Spring boot
 */

@SpringBootApplication
@PropertySource("classpath:api.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	private static Class<Application> applicationClass = Application.class;

}
