package repository.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipeApi {

    @Headers({"X-RapidAPI-Host: " + "tasty.p.rapidapi.com",
            "X-RapidAPI-Key: " + "0f41e94224msh2b03949d4a80c28p1d6f17jsn3c59188eae36"})
    @GET("/recipes/list")
    Call<RecipesResponse> getRecipeList(@Query("from") int from, @Query("size") int size);

    @Headers({"X-RapidAPI-Host: " + "tasty.p.rapidapi.com",
            "X-RapidAPI-Key: " + "0f41e94224msh2b03949d4a80c28p1d6f17jsn3c59188eae36"})
    @GET("/recipes/auto-complete")
    Call<RecipesResponse> getRecipe(@Query("prefix") String name);
}
