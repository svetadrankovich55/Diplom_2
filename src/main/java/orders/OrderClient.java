package orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import utils.Client;

public class OrderClient extends Client {

    protected final String ROOT = "/orders";

    @Step("Send POST request to /api/orders for creation order without authorization")
    public ValidatableResponse createWithoutAuthorization(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Send POST request to /api/orders for creation order with authorization")
    public ValidatableResponse createWithAuthorization(String token, Order order) {
        return spec()
                .auth().oauth2(token.replace("Bearer ", ""))
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Send GET request to /api/orders for getting list orders of user")
    public OrdersOfUser gettingOrdersOfUser(String token) {
        return spec()
                .auth().oauth2(token.replace("Bearer ", ""))
                .get(ROOT)
                .body().as(OrdersOfUser.class);
    }

    @Step("Send GET request to /api/orders for getting list orders of user without authorization")
    public ValidatableResponse gettingOrdersOfUserWithoutAuthorization() {
        return spec()
                .get(ROOT)
                .then().log().all();
    }

}
