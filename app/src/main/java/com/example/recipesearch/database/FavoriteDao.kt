package com.example.recipesearch.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Insert
    fun insertAll(vararg favorites: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}


