package com.swayy.core_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val idCharacter: Int? = null,
    val id: String,
    val image: String?,
    val name: String?,
    )