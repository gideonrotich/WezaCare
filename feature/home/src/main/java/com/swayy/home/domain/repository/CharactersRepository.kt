package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<Resource<List<CharacterModel>>>
}