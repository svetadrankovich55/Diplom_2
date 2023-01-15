package user;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String login, String password) {
        this.email = login;
        this.password = password;
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user.getEmail(), user.getPassword());
    }

    public static UserCredentials fromWithEmptyEmail(User user) {
        return new UserCredentials("", user.getPassword());
    }

    public static UserCredentials fromWithEmptyPassword(User user) {
        return new UserCredentials(user.getEmail(), "");
    }

    public static UserCredentials fromWithIncorrectEmail(User user) {
        return new UserCredentials("Uzumaki@dattebayo.com", user.getPassword());
    }

    public static UserCredentials fromWithIncorrectPassword(User user) {
        return new UserCredentials(user.getEmail(), "P@ssw0rd456");
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
