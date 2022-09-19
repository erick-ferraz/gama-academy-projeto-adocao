package com.academy.gama.projeto.adocao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.academy.gama.projeto.adocao.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("API Adoção de pets")
                .description("API de Adoção de Pets desenvolvida para o bootcamp Dev for Tech da Gama Academy \n " +
                        "Desenvolvedores: Erick Ferraz, Priscilla Alves, Estom Jr")
                .termsOfServiceUrl("/service.html")
                .version("0.0.0")
                .build();
    }
}
