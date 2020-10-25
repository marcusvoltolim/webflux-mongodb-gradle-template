package com.apirest.webflux.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Setup swagger.
	 * Documentacao acessivel em http://<host>:<port>/swagger-ui/index.html
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.gradle.webflux.web.handler"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}

	private static ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("API")
			.description("API")
			.version("1.0")
			.contact(new Contact("Marcus Voltolim", "https://github.com/marcusvoltolim", "marcus.voltolim@gmail.com"))
			.build();
	}

}
