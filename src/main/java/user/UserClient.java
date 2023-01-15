package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import utils.Client;

public class UserClient extends Client {
    protected final String ROOT = "/auth";


    @Step("Send POST request to /api/auth/register for creation user")
    public ValidatableResponse create(User user) {
        return spec()
                .body(user)
                .when()
                .post(ROOT + "/register")
                .then().log().all();
    }

    @Step("Send POST request to /api/auth/login for login user")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return spec()
                .body(userCredentials)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }

    @Step("Send DELETE request to /api/auth/user for deletion user")
    public ValidatableResponse delete(String token) {
        return spec()
                .auth().oauth2(token.replace("Bearer ", ""))
                .when()
                .delete(ROOT + "/user")
                .then().log().all();
    }

    @Step("Send PATCH request to /api/auth/user for editing user with authorization")
    public ValidatableResponse editWithAuthorization(String token, User user) {
        return spec()
                .auth().oauth2(token.replace("Bearer ", ""))
                .body(user)
                .when()
                .patch(ROOT + "/user")
                .then().log().all();
    }

    @Step("Send PATCH request to /api/auth/user for editing user without authorization")
    public ValidatableResponse editWithoutAuthorization(User user) {
        return spec()
                .body(user)
                .when()
                .patch(ROOT + "/user")
                .then().log().all();
    }


}
