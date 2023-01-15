package ingredients;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

public class IngredientsAssertions {

    @Step("Check successful getting ingredients")
    public void successfulGetIngredients(List <Ingredient> ingredients) {
        MatcherAssert.assertThat(ingredients, notNullValue());
    }

}
