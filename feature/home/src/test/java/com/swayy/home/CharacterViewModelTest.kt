package com.swayy.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.MutableLiveData
import com.swayy.core.util.Resource
import com.swayy.core_network.model.harrypotter.Wand
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.domain.use_case.GetCharactersUseCase
import com.swayy.home.presentation.home.CharactersViewModel
import com.swayy.home.presentation.home.components.SearchWidgetState
import com.swayy.home.presentation.home.state.CharactersState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: CharactersViewModel
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        getCharactersUseCase = mockk()
        viewModel = CharactersViewModel(getCharactersUseCase)
    }

    @Test
    fun `getCharacters updates characters state`() = testScope.runBlockingTest {
        // Given
        val expectedState = CharactersState(characters = listOf())

        coEvery { getCharactersUseCase.invoke() } returns flowOf(
            Resource.Success(
                listOf(
                    CharacterModel(
                        "Harry Potter",
                        true,
                        listOf(""),
                        listOf(""),
                        "djhds",
                        "hdfhdf",
                        "dsnd",
                        "male",
                        "hjds",
                        true,
                        false,
                        "hdhd",
                        "dhhd",
                        "",
                        "hdks",
                        "dhdhd",
                        "",
                        Wand("", 2.0, ""),
                        true,
                        3
                    ),
                )
            )
        )

        // When
        viewModel.getCharacters()

        // Then
        assertEquals(expectedState, viewModel.characters.value)
    }
}
