package com.example.letseat.repository.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipeApi {

    String HOST = "X-RapidAPI-Host: tasty.p.rapidapi.com";
    String KEY = "X-RapidAPI-Key: c4ac8e031amsh935622089237fdfp1552f4jsn6d24710d79ba";

    @Headers({HOST, KEY})
    @GET("/recipes/list")
    Call<RecipesResponse> getRecipeList(@Query("from") int from, @Query("size") int size);

    @Headers({HOST, KEY})
    @GET("/recipes/list")
    Call<RecipesResponse> getIngredientsList(@Query("from") int from, @Query("size") int size, @Query("q") ArrayList<String> ingredients);
}
