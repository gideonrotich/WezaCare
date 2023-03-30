package com.swayy.core_database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.swayy.core_database.model.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterEntity: List<CharacterEntity>)

    @Query("SELECT * FROM characters_table")
    fun getCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters_table")
    suspend fun deleteCharacters()
}