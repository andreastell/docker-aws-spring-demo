package se.callistaenterprise.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.callistaenterprise.demo.api.controller.data.ApiMetadata;

@Configuration
public class DemoAppConfiguration  {


    @Value("${info.app.version}")
    private String version;

    @Value("${info.app.build.timestamp}")
    private String buildTimestamp;

    @Value("${info.app.build.number}")
    private String buildNumber;

    @Bean
    public ApiMetadata apiMetadata() {
        return new ApiMetadata(version, buildNumber, buildTimestamp);
    }


}
