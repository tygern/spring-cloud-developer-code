package io.pivotal.pal.tracker.restsupport;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

public class ServiceLocator {

    private EurekaClient eurekaClient;

    public ServiceLocator(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    public String lookUpServiceUrl(String serviceName) {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(serviceName, false);
        return instanceInfo.getHomePageUrl();
    }
}
