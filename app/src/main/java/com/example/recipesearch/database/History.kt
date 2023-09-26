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

)
