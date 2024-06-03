package server.website.setup;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        System.out.println("JerseyConfig");
        packages("server.website.webservices");
    }
}
