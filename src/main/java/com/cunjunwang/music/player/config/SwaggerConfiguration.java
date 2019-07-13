package com.cunjunwang.music.player.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by CunjunWang on 2018/12/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${swagger.host}")
    private String swaggerHost;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cunjunwang.music.player.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("[nixie-music-player-service]")
                .description("Nixie音乐播放器后端服务")
                .version("0.0.1")
                .build();
    }

}