package server.website.webservices;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@PreMatching
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // If it's a preflight request, abort the request with a 200 status code
        if (isPreflightRequest(requestContext)) {
            requestContext.abortWith(Response.status(Response.Status.OK).build());
            return;
        }
    }

    private boolean isPreflightRequest(ContainerRequestContext requestContext) {
        return requestContext.getRequest().getMethod().equals("OPTIONS") &&
                requestContext.getHeaderString("Access-Control-Request-Method") != null &&
                requestContext.getHeaderString("Access-Control-Request-Headers") != null;
    }
}