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
package com.swayy.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.swayy.core.data.HarryPotterPreferences
import com.swayy.core.data.UserDataRepositoryImpl
import com.swayy.core.domain.UserDataRepository
import com.swayy.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Provides
    @Singleton
    fun provideHarryPotterPreferences(dataStore: DataStore<Preferences>) =
        HarryPotterPreferences(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(harryPotterPreferences: HarryPotterPreferences): UserDataRepository {
        return UserDataRepositoryImpl(harryPotterPreferences = harryPotterPreferences)
    }

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(Constants.HARRYPOTTER_PREFERENCES)
            }
        )
}
