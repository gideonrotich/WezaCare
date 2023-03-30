package com.swayy.home.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<Resource<List<CharacterModel>>> {
        return repository.getCharacters()
    }
}