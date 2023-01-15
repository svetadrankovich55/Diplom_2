import ingredients.Ingredient;
import ingredients.IngredientsAssertions;
import ingredients.IngredientsClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.Order;
import orders.OrderAssertions;
import orders.OrderClient;
import orders.OrderGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

import java.util.List;


public class CreateOrderTest {

    private final IngredientsClient ingredientsClient = new IngredientsClient();
    private final IngredientsAssertions ingredientsAssertions = new IngredientsAssertions();
    private final UserGenerator generatorUser = new UserGenerator();
    private final OrderGenerator generatorOrder = new OrderGenerator();
    private final OrderClient orderClient = new OrderClient();
    private final OrderAssertions orderAssertions = new OrderAssertions();
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
    @DisplayName("Successful creation order with authorization")
    public void successfulCreateUserWithAuthorization() {
        User user = generatorUser.random();
        ValidatableResponse creationUserResponse = userClient.create(user);
        userAssertions.successfulCreation(creationUserResponse);

        UserCredentials userCredentials = UserCredentials.from(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        accessToken = userAssertions.successfulLogin(loginResponse);

        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.random();
        ValidatableResponse creationOrderResponse = orderClient.createWithAuthorization(accessToken, order);
        orderAssertions.successfulCreationWithAuthorization(creationOrderResponse);

    }

    @Test
    @DisplayName("Successful creation order without authorization")
    public void successfulCreateUserWithoutAuthorization() {
        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.generic();
        ValidatableResponse creationResponse = orderClient.createWithoutAuthorization(order);
        orderAssertions.successfulCreationWithoutAuthorization(creationResponse);

    }

    @Test
    @DisplayName("Failed creation order without authorization and without ingredients")
    public void failedCreateUserWithoutAuthorizationAndIngredients() {
        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.genericWithoutIngredients();
        ValidatableResponse creationResponse = orderClient.createWithoutAuthorization(order);
        orderAssertions.failedCreationWithoutIngredients(creationResponse);

    }

    @Test
    @DisplayName("Failed creation order with authorization and without ingredients")
    public void failedCreateUserWithAuthorizationAndWithoutIngredients() {
        User user = generatorUser.random();
        ValidatableResponse creationUserResponse = userClient.create(user);
        userAssertions.successfulCreation(creationUserResponse);

        UserCredentials userCredentials = UserCredentials.from(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        accessToken = userAssertions.successfulLogin(loginResponse);

        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.genericWithoutIngredients();
        ValidatableResponse creationResponse = orderClient.createWithoutAuthorization(order);
        orderAssertions.failedCreationWithoutIngredients(creationResponse);

    }

    @Test
    @DisplayName("Failed creation order without authorization and with incorrect ingredients")
    public void failedCreateUserWithoutAuthorizationAndIncorrectIngredients() {
        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.genericIncorrectIngredients();
        ValidatableResponse creationResponse = orderClient.createWithoutAuthorization(order);
        orderAssertions.failedCreationWithIncorrectIngredients(creationResponse);

    }

    @Test
    @DisplayName("Failed creation order with authorization and with incorrect ingredients")
    public void failedCreateUserWithAuthorizationAndIncorrectIngredients() {
        User user = generatorUser.random();
        ValidatableResponse creationUserResponse = userClient.create(user);
        userAssertions.successfulCreation(creationUserResponse);

        UserCredentials userCredentials = UserCredentials.from(user);
        ValidatableResponse loginResponse = userClient.login(userCredentials);
        accessToken = userAssertions.successfulLogin(loginResponse);

        List<Ingredient> ingredients = ingredientsClient.getIngredientList();
        ingredientsAssertions.successfulGetIngredients(ingredients);

        Order order = generatorOrder.genericIncorrectIngredients();
        ValidatableResponse creationResponse = orderClient.createWithoutAuthorization(order);
        orderAssertions.failedCreationWithIncorrectIngredients(creationResponse);

    }

}
