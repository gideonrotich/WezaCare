package com.swayy.home.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.domain.use_case.GetCharactersUseCase
import com.swayy.home.presentation.home.components.SearchWidgetState
import com.swayy.home.presentation.home.state.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

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