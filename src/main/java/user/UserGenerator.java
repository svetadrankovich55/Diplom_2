package user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public User generic() {
        return new User("Uzumaki@dattebayo.com", "P@ssw0rd123", "Naruto");
    }

    public User random() {
        return new User(RandomStringUtils.randomAlphanumeric(10) + "@dattebayo.com", RandomStringUtils.randomAlphanumeric(10), "Naruto");
    }

    public User editing() {
        return new User("sharingan@uchiha.com", "Sasuke");
    }

    public User editingWithExistingEmail() {
        return new User("Uzumaki@dattebayo.com", "Naruto");
    }

    public User genericUserWithEmptyLogin() {
        return new User("", "P@ssw0rd123", "Naruto");
    }

    public User genericUserWithEmptyPassword() {
        return new User("Uzumaki@dattebayo.com", "", "Naruto");
    }

    public User genericUserWithEmptyName() {
        return new User("Uzumaki@dattebayo.com", "P@ssw0rd123", "");
    }
}
