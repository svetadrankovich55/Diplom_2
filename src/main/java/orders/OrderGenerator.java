package orders;

import ingredients.IngredientGenerator;

public class OrderGenerator {
    private final IngredientGenerator ingredientGenerator = new IngredientGenerator();

    public Order generic() {
        return new Order(ingredientGenerator.generic());
    }

    public Order random() {
        return new Order(ingredientGenerator.random());
    }

    public Order genericWithoutIngredients() {
        return new Order(ingredientGenerator.genericWithoutIngredients());
    }

    public Order genericIncorrectIngredients() {
        return new Order(ingredientGenerator.genericIncorrectIngredients());
    }
}
