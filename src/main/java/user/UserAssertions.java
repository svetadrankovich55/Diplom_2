package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class UserAssertions {

    @Step("Check successful creation user")
    public String successfulCreation(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract()
                .path("accessToken");
    }

    @Step("Check failed creation user with empty login or password  or name")
    public void failedCreationWithEmptyLoginOrPasswordOrName(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Check failed creation user with with existing user")
    public void failedCreationWithExistingUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("User already exists"));
    }

    @Step("Check successful login user")
    public String successfulLogin(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken");
    }

    @Step("Check failed login user with empty login or password")
    public void failedLoginWithEmptyLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Step("Check failed login user with incorrect login or password")
    public void failedLoginWithIncorrectLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Step("Check successful deletion user")
    public void successfulDelete(ValidatableResponse response) {
        response.assertThat()
                .statusCode(202)
                .body("success", is(true))
                .body("message", equalTo("User successfully removed"));
    }

    @Step("Check successful editing user")
    public void successfulEditing(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo("sharingan@uchiha.com"))
                .body("user.name", equalTo("Sasuke"));
    }

    @Step("Check successful editing user")
    public void failedEditingWithoutAuthorization(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Step("Check successful editing user with existing email")
    public void failedEditingWithExistingEmail(ValidatableResponse response) {
        response.assertThat()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo("User with such email already exists"));
    }

}
