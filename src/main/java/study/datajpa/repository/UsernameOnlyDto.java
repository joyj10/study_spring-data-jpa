package study.datajpa.repository;

/**
 * UsernameOnlyDto
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public class UsernameOnlyDto {
    private final String username;

    public UsernameOnlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
