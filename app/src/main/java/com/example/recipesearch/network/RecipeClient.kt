package com.example.recipesearch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RecipeClient {
    private const val BASE_URL = "https://api.edamam.com/api/"
    val instance : RecipeApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RecipeApi::class.java)
    }
}