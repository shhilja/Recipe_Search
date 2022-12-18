package com.example.recipesearch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity(tableName = "History")
data class History(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipe_name") val recipeName: String?

    constructor(val id: Int, val recipeName: String?) {

}
/*    @ColumnInfo(name = "img_src") var imgSrc: String?,
    @ColumnInfo(name = "diet_label") var dietLabel: String?,
    @ColumnInfo(name = "health_label") var healthLabel: String?,
    @ColumnInfo(name = "meal_type") var mealType: String?,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean*/
)
