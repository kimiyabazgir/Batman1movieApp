package com.example.batmansmoviesapplication.db


import androidx.room.*
import com.example.batmansmoviesapplication.utils.Constants
import kotlinx.coroutines.flow.Flow


@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(entity: MoviesEntity)

    @Query("SELECT * FROM ${Constants.Movies_DB_TABLE} ORDER BY id ASC")
    fun loadMovies(): Flow<List<MoviesEntity>>

}