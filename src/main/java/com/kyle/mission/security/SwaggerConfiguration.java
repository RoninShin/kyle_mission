package com.kyle.mission.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// @EnableWebMvc
// @Profile({"local","dev"})
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization") //헤더 이름
                .description("Access Tocken") //설명
                .modelRef(new ModelRef("string"))
                .parameterType("header") 
                .required(false)
                .build();

        List aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo())
                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kyle.mission.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }
 
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("앱 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                .license("Kyle").licenseUrl("https://kyle79.tistory.com/").version("1").build();
    }
}