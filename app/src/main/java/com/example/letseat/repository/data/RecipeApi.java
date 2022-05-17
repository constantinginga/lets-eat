package com.example.letseat.repository.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipeApi {

    String HOST = "X-RapidAPI-Host: tasty.p.rapidapi.com";
    String KEY = "X-RapidAPI-Key: 1239827863msh479f7c6972f12c6p19f903jsnc17bc3d1b156";

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
