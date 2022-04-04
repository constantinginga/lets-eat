package repository.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static RecipeApi recipeApi;

    public static RecipeApi getRecipeApi() {
        if (recipeApi == null) {
            recipeApi = new Retrofit.Builder()
                    .baseUrl("https://tasty.p.rapidapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecipeApi.class);
        }

        return recipeApi;
    }
}
