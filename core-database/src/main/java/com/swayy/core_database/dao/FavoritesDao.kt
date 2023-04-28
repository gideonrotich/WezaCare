package com.swayy.core_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.swayy.core_database.model.FavoriteEntity

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertAFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorites_table ORDER BY id DESC")
    fun getFavorites():LiveData<List<FavoriteEntity>>



}