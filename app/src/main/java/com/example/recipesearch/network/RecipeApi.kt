package com.example.recipesearch.network

import com.example.recipesearch.models.RecipeHits
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface RecipeApi {
    @GET("recipes/v2")
    fun getRecipeHits(
        @Query("type") type:String = "public",
        @Query("q") q : String = "salmon",
        @Query("app_id") app_id : String = "",
        @Query("app_key") app_key : String = ""

    ): Call<RecipeHits>

    @GET("recipes/v2")
    fun getRecipeByTime(
        @Query("mealType") mealType: String = "Breakfast",
        @Query("type") type:String = "public",
        @Query("app_id") app_id : String = "",
        @Query("app_key") app_key : String = ""

    ): Call<RecipeHits>



}
