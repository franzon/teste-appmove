package com.example.testeappmoove.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testeappmoove.data.entities.LikedMovie

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: LikedMovie)

    @Delete
    fun delete(movie: LikedMovie)

    @Query("SELECT * FROM liked_movies")
    fun all(): List<LikedMovie>
}