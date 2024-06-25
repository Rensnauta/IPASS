package server.website.webservices;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import server.website.Model.LogonRequest;
import server.website.Model.MyUser;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;
/**
 * The type Authentication resource.
 */
@PermitAll
@Path("authentication")
public class AuthenticationResource {
    /**
     * The constant key.
     */
    public static final Key key = MacProvider.generateKey();
    /**
     * Authenticate user response.
     *
     * @param logonRequest the logon request
     * @return the response
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest) {
        try {
// Authenticate the user using the credentials provided
            String role = MyUser.validateLogin(logonRequest.username(),
                    logonRequest.password());
            if (role == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
// Issue a token for the user
            String token = createToken(logonRequest.username(), role);
// Return the token on the response
            return Response.ok(Map.of("JWT", token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    /**
     * This method creates a token for a user with a specific role.
     * It uses the Jwts.builder() to create a token with a subject, expiration date
     and a claim.
     * It signs the token with the key and returns the token.
     *
     * @param username the username
     * @param role the role
     * @return the string
     * @throws JwtException the jwt exception
     */
    private String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
