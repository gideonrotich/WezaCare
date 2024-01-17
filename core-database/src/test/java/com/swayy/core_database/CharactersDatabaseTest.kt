package com.swayy.core_database

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.gson.Gson
import com.swayy.core_database.converters.Converters
import com.swayy.core_database.dao.CharacterDao
import com.swayy.core_database.model.CharacterEntity
import com.swayy.core_network.model.harrypotter.Wand
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersDatabaseTest {

    private lateinit var dao: CharacterDao
    private lateinit var db: CharactersDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val converters = Converters(Gson())

        db = Room.inMemoryDatabaseBuilder(
            context, CharactersDatabase::class.java
        )
            .addTypeConverter(converters)
            .allowMainThreadQueries()
            .build()
        dao = db.characterDao
    }

    @Test
    fun `test adding empty list followed by valid data`() = runTest {
        dao.insertCharacters(emptyList())

        Truth.assertThat(dao.getCharacters().isEmpty())

        dao.insertCharacters(
            listOf(
                CharacterEntity(
                    actor = "Gideon",
                    alive = true,
                    alternate_actors = emptyList(),
                    alternate_names = emptyList(),
                    ancestry = "Chumo",
                    dateOfBirth = "2002",
                    eyeColour = "white",
                    gender = "male",
                    hairColour = "black",
                    hogwartsStaff = false,
                    hogwartsStudent = true,
                    house = "harryp",
                    id = "1",
                    image = "",
                    name = "gede",
                    patronus = "Alliance",
                    species = "animalia",
                    wand = Wand("", 0.0, ""),
                    wizard = true,
                    yearOfBirth = 2001
                )
            )
        )

        Truth.assertThat(dao.getCharacters().size).isEqualTo(1)
    }

    @Test
    fun `adding fav to db returns valid data on query`() = runTest {
        val entity = CharacterEntity(
            actor = "Gideon",
            alive = true,
            alternate_actors = emptyList(),
            alternate_names = emptyList(),
            ancestry = "Chumo",
            dateOfBirth = "2002",
            eyeColour = "white",
            gender = "male",
            hairColour = "black",
            hogwartsStaff = false,
            hogwartsStudent = true,
            house = "harryp",
            id = "1",
            image = "",
            name = "gede",
            patronus = "Alliance",
            species = "animalia",
            wand = Wand("", 0.0, ""),
            wizard = true,
            yearOfBirth = 2001
        )

        dao.insertCharacters(listOf(entity) )

        Truth.assertThat(dao.getCharacters()).containsExactly(entity)
    }

    @Test
    fun `deleting fav to db updates properly`() = runTest {
        val entity = CharacterEntity(
            actor = "Gideon",
            alive = true,
            alternate_actors = emptyList(),
            alternate_names = emptyList(),
            ancestry = "Chumo",
            dateOfBirth = "2002",
            eyeColour = "white",
            gender = "male",
            hairColour = "black",
            hogwartsStaff = false,
            hogwartsStudent = true,
            house = "harryp",
            id = "1",
            image = "",
            name = "gede",
            patronus = "Alliance",
            species = "animalia",
            wand = Wand("", 0.0, ""),
            wizard = true,
            yearOfBirth = 2001
        )

        val entityTwo = CharacterEntity(
            actor = "Brian",
            alive = true,
            alternate_actors = emptyList(),
            alternate_names = emptyList(),
            ancestry = "Chumo",
            dateOfBirth = "2002",
            eyeColour = "white",
            gender = "male",
            hairColour = "black",
            hogwartsStaff = false,
            hogwartsStudent = true,
            house = "harryp",
            id = "2",
            image = "",
            name = "gede",
            patronus = "Alliance",
            species = "animalia",
            wand = Wand("", 0.0, ""),
            wizard = true,
            yearOfBirth = 2001
        )

        dao.insertCharacters(listOf(entity,entityTwo) )

        Truth.assertThat(dao.getCharacters().size).isEqualTo(2)

        dao.deleteCharacters(listOf(entity.id) )
        Truth.assertThat(dao.getCharacters().size).isEqualTo(1)

        dao.deleteCharacters(listOf(entityTwo.id) )
        Truth.assertThat(dao.getCharacters()).isEmpty()
    }

}