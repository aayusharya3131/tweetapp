/**
 * 
 */
package com.tweetApp.tweetApp;

import static com.google.common.base.Predicates.and;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 *
 * @author 144690
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Instantiates a new swagger config.
	 */
	SwaggerConfig() {
	}

	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public  Docket api() {
		@SuppressWarnings("unchecked")
		Predicate<RequestHandler> apiPredicates = and(
				basePackage("com.tweetapp.tweet.controller"));
		return new Docket(DocumentationType.SWAGGER_2).select().apis(apiPredicates).paths(PathSelectors.any()).build().apiInfo(apiEndPointsInfo());
	}
	private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Configuration API Documentation")
            .description("This will help understand the detail of each Microservices.")            
            .version("1.0.0")
            .build();
    }
}
