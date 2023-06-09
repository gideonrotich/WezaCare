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
package com.swayy.home.di

import com.swayy.core.work.NetworkChangeList
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_network.HarryPotterApi
import com.swayy.home.data.repository.CharacterRepositoryImpl
import com.swayy.home.domain.repository.CharactersRepository
import com.swayy.home.domain.repository.NiaNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideNiaNetworkDataSource(): NiaNetworkDataSource {
        return NiaNetworkDataSourceImpl()
    }
    @Provides
    @Singleton
    fun provideCharactersRepository(
        harryPotterApi: HarryPotterApi,
        characterDao: CharacterDao,
        network:NiaNetworkDataSource
    ): CharactersRepository {
        return CharacterRepositoryImpl(
            harryPotterApi = harryPotterApi, characterDao = characterDao,network = network
        )
    }
}

class NiaNetworkDataSourceImpl : NiaNetworkDataSource {
    override suspend fun getTopicChangeList(after: Int?): List<NetworkChangeList> {
        TODO("Not yet implemented")
    }

}
