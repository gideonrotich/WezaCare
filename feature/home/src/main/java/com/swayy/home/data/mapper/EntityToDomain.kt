package com.swayy.home.data.mapper

import com.swayy.core_database.model.CharacterEntity
import com.swayy.home.domain.model.CharacterModel

internal fun CharacterEntity.toDomain(): CharacterModel {
    return CharacterModel(
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
        id,
        image,
        name,
        patronus,
        species,
        wand,
        wizard,
        yearOfBirth
    )
}