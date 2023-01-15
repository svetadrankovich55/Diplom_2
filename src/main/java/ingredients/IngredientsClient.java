package ingredients;

import io.qameta.allure.Step;
import utils.Client;

import java.util.List;

public class IngredientsClient extends Client {

    protected final String ROOT = "/ingredients";

    @Step("Send GET request to /api/ingredients for getting actual list of ingredients")
    public List<Ingredient> getIngredientList() {

        ActualIngredients actualIngredients = spec()
                .get(ROOT)
                .body().as(ActualIngredients.class);

        return actualIngredients.getData();
    }
}
