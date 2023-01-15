package ingredients;

import java.util.List;

public class Database {

    private final List<Ingredient> ingredients;

    private final IngredientsClient ingredientsClient = new IngredientsClient();

    public Database() {
        ingredients = ingredientsClient.getIngredientList();
    }

    public List<Ingredient> availableIngredients() {
        return ingredients;
    }

    public Ingredient availableIngredient(int index) {
        return ingredients.get(index);
    }
}
