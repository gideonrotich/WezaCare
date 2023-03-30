package com.swayy.home.data.repository

import androidx.lifecycle.map
import com.swayy.core.util.Resource
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_network.HarryPotterApi
import com.swayy.home.data.mapper.toDomain
import com.swayy.home.data.mapper.toEntity
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CharacterRepositoryImpl(
    private val harryPotterApi: HarryPotterApi,
    private val characterDao: CharacterDao
) : CharactersRepository {
    override fun getCharacters(): Flow<Resource<List<CharacterModel>>> = flow {
        emit(Resource.Loading())

        val getCharactersFromDb = characterDao.getCharacters().map { it.toDomain() }
        emit(Resource.Loading(data = getCharactersFromDb))

        try {
            val apiResponse = harryPotterApi.getCharacters()
            characterDao.deleteCharacters()
            characterDao.insertCharacters(apiResponse.map { it.toEntity() })
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = "Connection Lost",
                    data = getCharactersFromDb
                )
            )
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = getCharactersFromDb
                )
            )
        }
        val allCharacters = characterDao.getCharacters().map { it.toDomain() }
        emit(Resource.Success(allCharacters))
    }

}