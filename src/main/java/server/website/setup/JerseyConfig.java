package server.website.setup;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import server.website.webservices.CORSFilter;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("server.website.webservices", "server.website.Model");
        register(RolesAllowedDynamicFeature.class);
        register(CORSFilter.class);
    }
}
