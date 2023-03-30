package com.swayy.core_database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.swayy.core_database.CharactersDatabase
import com.swayy.core_database.converters.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideTypeConverters(gson: Gson) =
        Converters(gson)

    @Provides
    @Singleton
    fun provideCharactersDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ): CharactersDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CharactersDatabase::class.java,
            "CharactersDatabase"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(converters)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideContractDao(database: CharactersDatabase) = database.characterDao
}