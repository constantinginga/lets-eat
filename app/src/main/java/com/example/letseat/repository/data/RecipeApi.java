package com.example.letseat.repository.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipeApi {

    String HOST = "X-RapidAPI-Host: tasty.p.rapidapi.com";
    String KEY = "X-RapidAPI-Key: 0f41e94224msh2b03949d4a80c28p1d6f17jsn3c59188eae36";

    @Headers({HOST, KEY})
    @GET("/recipes/list")
    Call<RecipesResponse> getRecipeList(@Query("from") int from, @Query("size") int size);

    @Headers({HOST, KEY})
    @GET("/recipes/list")
    Call<RecipesResponse> getIngredientsList(@Query("from") int from, @Query("size") int size, @Query("q") ArrayList<String> ingredients);

    @Headers({HOST, KEY})
    @GET("recipes/get-more-info")
    Call<RecipeResponse> getRecipeInfo(@Query("id") int id);
}
