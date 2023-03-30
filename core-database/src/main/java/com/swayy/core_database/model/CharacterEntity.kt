package com.swayy.core_database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.core_network.model.harrypotter.Wand
import kotlinx.parcelize.Parcelize


@Entity(tableName = "characters_table")
data class CharacterEntity(
    val actor: String?,
    val alive: Boolean?,
    val alternate_actors: List<String>?,
    val alternate_names: List<String>?,
    val ancestry: String?,
    val dateOfBirth: String?,
    val eyeColour: String?,
    val gender: String?,
    val hairColour: String?,
    val hogwartsStaff: Boolean?,
    val hogwartsStudent: Boolean?,
    val house: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String?,
    val name: String?,
    val patronus: String?,
    val species: String?,
    val wand: Wand?,
    val wizard: Boolean?,
    val yearOfBirth: Int?
)
