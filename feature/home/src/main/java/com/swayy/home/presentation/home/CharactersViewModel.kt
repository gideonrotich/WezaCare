/*
 * Copyright 2023 GradleBuildPlugins
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
package com.swayy.home.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.use_case.GetCharactersUseCase
import com.swayy.home.presentation.home.components.SearchWidgetState
import com.swayy.home.presentation.home.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characters = mutableStateOf(CharactersState())
    val characters: State<CharactersState> = _characters

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    private val _searchString = mutableStateOf("")
    val searchString: State<String> = _searchString

    fun setSearchString(value: String) {
        _searchString.value = value
    }

    init {
        getCharacters()
    }

    fun getCharacters(searchString: String = "") {
        _characters.value = characters.value.copy(
            isLoading = true,
            error = "",
            characters = emptyList()
        )
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _characters.value = CharactersState(
                        characters = if (searchString.isEmpty()) {
                            result.data ?: emptyList()
                        } else {
                            result.data?.filter { character ->
                                character.name?.contains(searchString, ignoreCase = true) == true ||
                                    character.house?.contains(
                                    searchString,
                                    ignoreCase = true
                                ) == true
                            } ?: emptyList()
                        }
                    )
                }
                is Resource.Error -> {
                    _characters.value = CharactersState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _characters.value = CharactersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}