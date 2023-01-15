import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

public class LoginUserTest {

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
    @DisplayName("Successful login user")
    public void successfulLoginUser() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.successfulCreation(creationResponse);

        UserCredentials userCredentials = UserCredentials.from(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        accessToken = userAssertions.successfulLogin(loginResponse);

    }

    @Test
    @DisplayName("Failed login user with empty email")
    public void failedLoginCourierWithEmptyEmail() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.successfulCreation(creationResponse);

        UserCredentials userCredentials = UserCredentials.fromWithEmptyEmail(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        userAssertions.failedLoginWithEmptyLoginOrPassword(loginResponse);
    }

    @Test
    @DisplayName("Failed login user with empty password")
    public void failedLoginCourierWithEmptyPassword() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.successfulCreation(creationResponse);

        UserCredentials userCredentials = UserCredentials.fromWithEmptyPassword(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        userAssertions.failedLoginWithEmptyLoginOrPassword(loginResponse);
    }

    @Test
    @DisplayName("Failed login user with incorrect email")
    public void failedLoginCourierWithIncorrectEmail() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.successfulCreation(creationResponse);

        UserCredentials userCredentials = UserCredentials.fromWithIncorrectEmail(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        userAssertions.failedLoginWithIncorrectLoginOrPassword(loginResponse);
    }

    @Test
    @DisplayName("Failed login user with incorrect password")
    public void failedLoginCourierWithIncorrectPassword() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        userAssertions.successfulCreation(creationResponse);

        UserCredentials userCredentials = UserCredentials.fromWithIncorrectPassword(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        userAssertions.failedLoginWithIncorrectLoginOrPassword(loginResponse);
    }


}
