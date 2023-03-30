package com.swayy.home.data.mapper

import com.swayy.core_database.model.CharacterEntity
import com.swayy.core_network.model.harrypotter.HarrypotterResponseDtoItem
import com.swayy.home.domain.model.CharacterModel

internal fun HarrypotterResponseDtoItem.toEntity(): CharacterEntity {
    return CharacterEntity(
        actor,
        alive,
        alternate_actors,
        alternate_names,
        ancestry,
        dateOfBirth,
        eyeColour,
        gender,
        hairColour,
        hogwartsStaff,
        hogwartsStudent,
        house,
        id!!,
        image,
        name,
        patronus,
        species,
        wand,
        wizard,
        yearOfBirth
    )
}