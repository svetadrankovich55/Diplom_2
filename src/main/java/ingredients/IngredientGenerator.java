package ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IngredientGenerator {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<Ingredient> ingredientsRandom = new ArrayList<>();
    private final Database database = new Database();


    public List<Ingredient> generic() {
        ingredients.add(database.availableIngredient(0));
        ingredients.add(database.availableIngredient(1));

        return ingredients;
    }

    public List<Ingredient> random() {
        Random rand = new Random();
        ingredientsRandom.add(database.availableIngredient(rand.nextInt(database.availableIngredients().size())));
        ingredientsRandom.add(database.availableIngredient(rand.nextInt(database.availableIngredients().size())));
        ingredientsRandom.add(database.availableIngredient(rand.nextInt(database.availableIngredients().size())));

        return ingredientsRandom;
    }

    public List<Ingredient> genericWithoutIngredients() {
        return ingredients;
    }

    public List<Ingredient> genericIncorrectIngredients() {
        ingredients.add(new Ingredient("61c0c5a71d1f82001bdaaa6dtest"));
        ingredients.add(new Ingredient("61c0c5a71d1f82001bdaaa6dtest"));

        return ingredients;
    }
}
