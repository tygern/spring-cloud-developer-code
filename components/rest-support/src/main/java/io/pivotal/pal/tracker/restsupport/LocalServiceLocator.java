package io.pivotal.pal.tracker.restsupport;

import org.springframework.core.env.Environment;

public class LocalServiceLocator implements ServiceLocator {
    private final Environment environment;

    public LocalServiceLocator(Environment environment) {
        this.environment = environment;
    }

    public String lookUpServiceUrl(String serviceName) {
        switch(serviceName) {
            case "registration-server": {
                return environment.getProperty("service-locator.endpoint." + serviceName);
            }
            default: {
                throw new RuntimeException("Endpoint for service: " + serviceName + " not found");
            }
        }
    }
}
