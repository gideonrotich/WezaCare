package com.swayy.home.presentation.home.state

import com.swayy.home.domain.model.CharacterModel

data class CharactersState(
    val isLoading: Boolean = false,
    val error: String = "",
    val characters: List<CharacterModel> = emptyList()
)
