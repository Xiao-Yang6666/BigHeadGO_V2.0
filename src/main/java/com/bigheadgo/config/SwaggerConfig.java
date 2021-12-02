package com.bigheadgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Swagger API文档 配置
 *
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 0:20
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    //作者信息
    Contact contact = new Contact("没什么大不了小队", "bigheadgo.com", "1007006957@qq.com");

    // 配置了Swagger的Docket的bean实例
    // enable是否启动swagger，如果为False则Swagger不能在浏览器访问
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bigheadgo.controller"))
                .build();
    }

    //配置Swagger 信息 = ApiInfo
    private ApiInfo apiInfo() {
        return new ApiInfo("大头出行的Api文档",
                "后台接口文档,好好看",
                "v2.0",
                "bigheadgo.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }


}