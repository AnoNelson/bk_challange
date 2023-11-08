package com.challenge.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Collections;
import java.util.List;

//@EnableSwagger2
@Configuration
public class SwaggerConfig {
//    @Bean
//    public Docket swaggerConfiguration() {
//        //Prepare a docket instance
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .paths(PathSelectors.ant("/api/**"))
//                .apis(RequestHandlerSelectors.basePackage("com.challenge.challenge"))
//                .build()
//                .apiInfo(myOpenAPI());
//    }
//    private ApiInfo apiDetails() {
//        return new ApiInfo(
//                "Company",
//                "A tech company that serve clients ",
//                "1.0",
//                null,
//                new springfox.documentation.service.Contact("Bk_challenge", "https://bk.rw", "info@bk.rw"),
//                "API License",
//                "https://bk.rw",
//                Collections.emptyList());
//    }
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl("prodUrl");
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("challenge@bk.com");
        contact.setName("Social media");
        contact.setUrl("https://challenge.bk.com");

        License nelsonLicense = new License().name("Nelson License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Social media API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Social media.").termsOfService("https://challenge.bk.com/terms")
                .license(nelsonLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}