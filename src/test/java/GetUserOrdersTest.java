import ingredients.Ingredient;
import ingredients.IngredientsAssertions;
import ingredients.IngredientsClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.*;
import org.junit.After;
import org.junit.Test;
import user.*;

import java.util.List;

public class GetUserOrdersTest {


    private final IngredientsClient ingredientsClient = new IngredientsClient();
    private final IngredientsAssertions ingredientsAssertions = new IngredientsAssertions();
    private final UserGenerator generatorUser = new UserGenerator();
    private final OrderGenerator generatorOrder = new OrderGenerator();
    private final OrderClient orderClient = new OrderClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();
    private final UserClient userClient = new UserClient();
    private final UserAssertions userAssertions = new UserAssertions();
    private String accessToken;


    @After
    public void deleteCourier() {
        if (accessToken != null) {
            ValidatableResponse response = userClient.delete(accessToken);
            userAssertions.successfulDelete(response);
        }
    }

    @Test
    @DisplayName("Successful getting list orders of user with authorization")
    public void successfulGettingListOrdersOfUserWithAuthorization() throws InterruptedException {
        User user = generatorUser.random();
        ValidatableResponse creationUserResponse = userClient.create(user);
        userAssertions.successfulCreation(creationUserResponse);
        Thread.sleep(100);

        UserCredentials userCredentials = UserCredentials.from(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        accessToken = userAssertions.successfulLogin(loginResponse);
        Thread.sleep(100);

        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);
        Thread.sleep(100);

        Order orderFirst = generatorOrder.random();
        ValidatableResponse creationOrderFirstResponse = orderClient.createWithAuthorization(accessToken, orderFirst);
        orderAssertions.successfulCreationWithAuthorization(creationOrderFirstResponse);
        Thread.sleep(100);

        Order orderSecond = generatorOrder.random();
        ValidatableResponse creationOrderSecondResponse = orderClient.createWithAuthorization(accessToken, orderSecond);
        orderAssertions.successfulCreationWithAuthorization(creationOrderSecondResponse);
        Thread.sleep(100);

        Order orderThird = generatorOrder.random();
        ValidatableResponse creationOrderThirdResponse = orderClient.createWithAuthorization(accessToken, orderThird);
        orderAssertions.successfulCreationWithAuthorization(creationOrderThirdResponse);
        Thread.sleep(100);

        OrdersOfUser ordersOfUser = orderClient.gettingOrdersOfUser(accessToken);
        orderAssertions.successfulGettingOrdersWithAuthorizedUser(ordersOfUser);

    }

    @Test
    @DisplayName("Failed getting list orders without authorization")
    public void failedGettingListOrdersWithoutAuthorization() {

        ValidatableResponse gettingResponse = orderClient.gettingOrdersOfUserWithoutAuthorization();
        orderAssertions.failedGettingListOrdersWithoutAuthorization(gettingResponse);

    }
}
