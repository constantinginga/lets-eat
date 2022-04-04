package repository.data;

import repository.model.Recipe;

public class RecipeResponse {
    private String name;
    private String display;
    private int show_id;
    private String thumbnail_url;

    public Recipe getRecipe() {
        return new Recipe(show_id, (name != null) ? name : display, thumbnail_url);
    }
}
