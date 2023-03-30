package com.swayy.core_network.model.harrypotter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class HarrypotterResponseDtoItem(
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
    val id: String?,
    val image: String?,
    val name: String?,
    val patronus: String?,
    val species: String?,
    val wand: Wand?,
    val wizard: Boolean?,
    val yearOfBirth: Int?
)