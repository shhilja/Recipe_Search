package com.example.recipesearch.network

import com.example.recipesearch.models.RecipeHits
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//GET: recipes/v2
//appKey&ID: ?type=public&app_id=5bc59021&app_key=a25ecb8b5f75d554a674c8db2b857c39"

interface RecipeApi {
    @GET("recipes/v2")
    fun getRecipeHits(
        @Query("type") type:String = "public",
        @Query("q") q : String = "salmon",
        @Query("app_id") app_id : String = "5bc59021",
        @Query("app_key") app_key : String = "a25ecb8b5f75d554a674c8db2b857c39"

    ): Call<RecipeHits>

    @GET("recipes/v2")
    fun getRecipeByTime(
        @Query("mealType") mealType: String = "Breakfast",
        @Query("type") type:String = "public",
        @Query("app_id") app_id : String = "5bc59021",
        @Query("app_key") app_key : String = "a25ecb8b5f75d554a674c8db2b857c39"

    ): Call<RecipeHits>



}