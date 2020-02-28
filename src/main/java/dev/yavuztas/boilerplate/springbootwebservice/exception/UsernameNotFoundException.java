package dev.yavuztas.boilerplate.springbootwebservice.exception;

/**
 * Sample api business logic error when we cannot find the username
 *
 * @author Yavuz Tas
 */
public class UsernameNotFoundException extends RuntimeException {

    private final String username;

    public UsernameNotFoundException(String username) {
        this(username, "Username not found: " + username);
    }

    public UsernameNotFoundException(String username, String message) {
        super(message);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
