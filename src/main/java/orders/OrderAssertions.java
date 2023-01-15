package orders;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.*;

public class OrderAssertions {

    @Step("Check successful creation order without authorization")
    public void successfulCreationWithoutAuthorization(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .body("name", notNullValue())
                .body("order.number", greaterThan(0));
    }

    @Step("Check successful creation order with authorization")
    public void successfulCreationWithAuthorization(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .body("name", notNullValue())
                .body("order.number", greaterThan(0))
                .body("order.owner.name", equalTo("Naruto"));
    }

    @Step("Check failed creation order without ingredients")
    public void failedCreationWithoutIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("success", is(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Check failed creation order with incorrect ingredients")
    public void failedCreationWithIncorrectIngredients(ValidatableResponse response) {
        response.assertThat()
                .statusCode(500);
    }

    @Step("Check successful getting list orders of authorized user")
    public void successfulGettingOrdersWithAuthorizedUser(OrdersOfUser ordersOfUser) {
        MatcherAssert.assertThat(ordersOfUser, notNullValue());
        MatcherAssert.assertThat(ordersOfUser.getSuccess(), equalTo(true));
        MatcherAssert.assertThat(ordersOfUser.getOrders().size(), equalTo(3));

    }

    @Step("Check failed getting list orders without authorization")
    public void failedGettingListOrdersWithoutAuthorization(ValidatableResponse response) {
        response.assertThat()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo("You should be authorised"));
    }


}
