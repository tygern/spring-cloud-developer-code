package io.pivotal.pal.tracker.restsupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    private final Environment environment;
    private final EurekaClient eurekaClient;

    public RestConfig(Environment environment,
                      EurekaClient eurekaClient) {
        this.environment = environment;
        this.eurekaClient = eurekaClient;
    }

    @Bean
    public RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    ServiceLocator getServiceLocator() {
        String serviceLocatorType
                = environment.getProperty("service-locator");

        if (serviceLocatorType != null) {
            if(serviceLocatorType.equals("local-config"))
                return new LocalServiceLocator(environment);
            else return new EurekaServiceLocator(eurekaClient);
        } else return new EurekaServiceLocator(eurekaClient);

    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
