import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.User;
import user.UserAssertions;
import user.UserClient;
import user.UserGenerator;


public class CreateUserTest {

    private final UserGenerator generator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private final UserAssertions userAssertions = new UserAssertions();

    private String accessToken;

    @Before
    public void expect() throws InterruptedException {
        Thread.sleep(100);
    }

    @After
    public void deleteCourier() {
        if (accessToken != null) {
            ValidatableResponse response = userClient.delete(accessToken);
            userAssertions.successfulDelete(response);
        }
    }

    @Test
    @DisplayName("Successful creation user")
    public void successfulCreateUser() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        accessToken = userAssertions.successfulCreation(creationResponse);

    }

    @Test
    @DisplayName("Failed creation user with empty login")
    public void failedCreateUserWithEmptyLogin() {
        User user = generator.genericUserWithEmptyLogin();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.failedCreationWithEmptyLoginOrPasswordOrName(creationResponse);
    }

    @Test
    @DisplayName("Failed creation user with empty password")
    public void failedCreateUserWithEmptyPassword() {
        User user = generator.genericUserWithEmptyPassword();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.failedCreationWithEmptyLoginOrPasswordOrName(creationResponse);
    }

    @Test
    @DisplayName("Failed creation user with empty name")
    public void failedCreateUserWithEmptyName() {
        User user = generator.genericUserWithEmptyName();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.failedCreationWithEmptyLoginOrPasswordOrName(creationResponse);
    }

    @Test
    @DisplayName("Failed creation user with existing user")
    public void failedCreateWithExistingUser() {
        User user = generator.generic();
        userClient.create(user);
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.failedCreationWithExistingUser(creationResponse);
    }
}
