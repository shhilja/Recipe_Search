package com.example.recipesearch.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItems (
    val label: String,
    val image: String,
    val dietLabels: ArrayList<String>,
    val healthLabels: ArrayList<String>,
    val mealType: ArrayList<String>,
    val isFavorite: Boolean = false
        ): Parcelable