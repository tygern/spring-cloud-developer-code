package io.pivotal.pal.tracker.restsupport;

import com.netflix.discovery.EurekaClient;
import org.springframework.stereotype.Component;

public class EurekaServiceLocator implements ServiceLocator {
    private EurekaClient eurekaClient;

    public EurekaServiceLocator(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public String lookUpServiceUrl(String serviceName) {
        return eurekaClient.getNextServerFromEureka(serviceName,
                false).getHomePageUrl();
    }
}
