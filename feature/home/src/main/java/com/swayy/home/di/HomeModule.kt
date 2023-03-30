package com.swayy.home.di

import com.swayy.core_database.CharactersDatabase
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_network.HarryPotterApi
import com.swayy.home.data.repository.CharacterRepositoryImpl
import com.swayy.home.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        harryPotterApi: HarryPotterApi,
        characterDao: CharacterDao
    ): CharactersRepository {
        return CharacterRepositoryImpl(
            harryPotterApi = harryPotterApi, characterDao = characterDao
        )
    }

}