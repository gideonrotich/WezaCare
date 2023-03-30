package com.swayy.core_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.swayy.core_database.converters.Converters
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_database.model.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CharactersDatabase : RoomDatabase() {
    abstract val characterDao: CharacterDao
}