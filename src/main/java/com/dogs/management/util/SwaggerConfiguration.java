package com.dogs.management.util;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
        		.group("default")
				.pathsToMatch("/**")
				.packagesToScan("com.dogs.management")
				.build();
    }
}
