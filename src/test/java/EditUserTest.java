import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.User;
import user.UserAssertions;
import user.UserClient;
import user.UserGenerator;

public class EditUserTest {

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
    @DisplayName("Successful editing user with authorization")
    public void successfulEditeUser() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        accessToken = userAssertions.successfulCreation(creationResponse);

        User editingUser = generator.editing();
        ValidatableResponse editingResponse = userClient.editWithAuthorization(accessToken, editingUser);
        userAssertions.successfulEditing(editingResponse);

    }

    @Test
    @DisplayName("Failed editing user without authorization")
    public void failedEditeUserWithoutAuthorization() {
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        accessToken = userAssertions.successfulCreation(creationResponse);

        User editingUser = generator.editing();
        ValidatableResponse editingResponse = userClient.editWithoutAuthorization(editingUser);
        userAssertions.failedEditingWithoutAuthorization(editingResponse);

    }

    @Test
    @DisplayName("Failed editing user without authorization")
    public void failedEditeUserWithExistingEmail() {

        User existingUser = generator.generic();
        userClient.create(existingUser);
        User user = generator.random();
        ValidatableResponse creationResponse = userClient.create(user);
        accessToken = userAssertions.successfulCreation(creationResponse);

        User editingUser = generator.editingWithExistingEmail();
        ValidatableResponse editingResponse = userClient.editWithAuthorization(accessToken, editingUser);
        userAssertions.failedEditingWithExistingEmail(editingResponse);

    }


}
