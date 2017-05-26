package se.callistaenterprise.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.callistaenterprise.demo.api.controller.DemoController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger configuration
 * <p/>
 * http://springfox.github.io/springfox/docs/current/#springfox-swagger2-with-spring-mvc-and-spring-boot
 */
@Slf4j
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Value("${info.app.version}")
    private String version;


    @Bean
    public Docket docket() {
        // @formatter:off

        final Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(DemoController.class.getPackage().getName()))
                .build();

        return docket;

        // @formatter:on
    }


    private ApiInfo apiInfo() {
        // @formatter:off

        return new ApiInfoBuilder()
                .title("Demo")
                .description("Demo"                )
                .version(version)
                .contact(new Contact("Callista Enterprise AB", "http://www.callistaenterprise.se", ""))
                .build();

        // @formatter:on
    }
}