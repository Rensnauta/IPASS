package server.website.Model;

public class User implements java.security.Principal {
    private String userName;
    private String password;

    @Override
    public String getName() {
        return userName;
    }
}
