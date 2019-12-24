package com.sample.test.api.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : SwaggerConfig.java
 *
 * Clase para configurar Swagger y poder ver la definición de los REST
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.test.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "SAMPLE TEST API WEB",
                "API WEB con lo requerido en: https://github.com/cesaralcancio/simple-test",
                "1.0.0-SNAPSHOT",
                "Terminos del Servicio",
                new Contact("Andrés Prado Cruz", "", "andresss32157@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}