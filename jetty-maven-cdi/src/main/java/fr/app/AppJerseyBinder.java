package fr.app;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class AppJerseyBinder extends ResourceConfig {

    public AppJerseyBinder() {
        super(MultiPartFeature.class);
        String[] pacakgesToScan = {"fr.app"};
        packages(true, pacakgesToScan);
    }
}
