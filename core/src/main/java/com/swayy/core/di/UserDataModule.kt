package com.swayy.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.swayy.core.data.MealTimePreferences
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
    fun provideMealTimePreferences(dataStore: DataStore<Preferences>) =
        MealTimePreferences(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(mealTimePreferences: MealTimePreferences): UserDataRepository {
        return UserDataRepositoryImpl(mealTimePreferences = mealTimePreferences)
    }

    @Provides
    @Singleton
    fun provideDatastorePreferences(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(Constants.MEALTIME_PREFERENCES)
            }
        )

}
