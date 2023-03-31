/*
 * Copyright 2023 Gideon Rotich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.swayy.home.data.repository

import androidx.lifecycle.map
import com.swayy.core.util.Resource
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_network.HarryPotterApi
import com.swayy.home.data.mapper.toDomain
import com.swayy.home.data.mapper.toEntity
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.domain.repository.CharactersRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

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