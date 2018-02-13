package io.pivotal.pal.tracker.backlog;

import io.pivotal.pal.tracker.restsupport.ServiceLocator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

import java.util.TimeZone;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"io.pivotal.pal.tracker.backlog", "io.pivotal.pal.tracker.restsupport"})
public class App {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProjectClient projectClient(
            ServiceLocator serviceLocator,
            RestOperations restOperations
    ) {
        return new ProjectClient(serviceLocator,restOperations);
    }
}
