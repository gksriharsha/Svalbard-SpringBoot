package com.project.Svalbard.Configuration;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import com.project.Svalbard.Model.Angular.Dataset;
import com.project.Svalbard.Model.Angular.GeneralCard;
import com.project.Svalbard.Model.Angular.Platform;
import com.project.Svalbard.Model.Angular.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashMap;


@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket swaggerconfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Dataset.class)
                .ignoredParameterTypes(HashMap.class)
                .ignoredParameterTypes(GeneralCard.class)
                .ignoredParameterTypes(Platform.class)
                .ignoredParameterTypes(Results.class)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error.*"))).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Svalbard API",
                "Application to aggregate ML results",
                "1.0.0",
                "",
                new Contact("Krishna Sriharsha Gundu", "https://www.linkedin.com/in/gksriharsha", "gksriharsha@gmail.com"),
                "", "", Collections.emptyList());
    }
}
