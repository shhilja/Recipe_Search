package com.example.recipesearch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "recipe_name") val recieName: String?,
    @ColumnInfo(name = "img_src") val imgSrc: String?,
    @ColumnInfo(name = "diet_label") val dietLabel: String?,
    @ColumnInfo(name = "health_label") val healthLabel: String?,
    @ColumnInfo(name = "meal_type") val mealType: String?
)
