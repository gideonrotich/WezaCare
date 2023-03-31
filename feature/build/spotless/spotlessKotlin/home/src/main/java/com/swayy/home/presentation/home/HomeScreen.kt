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
package com.swayy.home.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.compose_ui.theme.BackgroundDarkColor
import com.swayy.compose_ui.theme.BackgroundDarkColorTwo
import com.swayy.core.R
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.presentation.home.components.MainAppBar
import com.swayy.home.presentation.home.components.SearchWidgetState

interface HomeNavigator {

    fun openHome()

    fun popBackStack()

    fun openCharacterDetails(characterId: String)
}

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun HomeScreen(
    navigator: HomeNavigator,
    viewModel: CharactersViewModel = hiltViewModel()
) {

    val charactersState = viewModel.characters.value
    val searchWidgetState by viewModel.searchWidgetState
    val searchString by viewModel.searchString
    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var charactersData: List<CharacterModel> = emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDarkColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundDarkColorTwo)
        ) {

            Image(
                painter = painterResource(id = R.drawable.harry),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp, top = 50.dp)
            )

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    MainAppBar(
                        searchWidgetState = searchWidgetState,
                        searchStringState = searchString,
                        onTextChange = {
                            viewModel.setSearchString(it)
                        },
                        onCloseClicked = {
                            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                            viewModel.getCharacters()
                        },
                        onSearchClicked = { _ ->
                            keyboardController?.hide()
                            viewModel.getCharacters(viewModel.searchString.value)
                        },
                        onSearchTriggered = {
                            viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp, top = 14.dp),
                backgroundColor = BackgroundDarkColorTwo
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .background(BackgroundDarkColor)

                ) {

                    LazyVerticalGrid(
                        GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                    ) {
                        items(charactersState.characters) { data ->
                            CharacterCard(
                                data = data,
                                openCharacterDetails = { characterId ->
                                    navigator.openCharacterDetails(characterId)
                                }
                            )
                        }
                    }
                    if (charactersState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CharacterCard(
    data: CharacterModel,
    openCharacterDetails: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material.Card(
            modifier = Modifier
                .size(160.dp, 180.dp)
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
                .clip(
                    RoundedCornerShape(10.dp)
                ),
            elevation = 4.dp,
            onClick = {
                openCharacterDetails(data.id!!)
            }

        ) {
            val image: Painter =
                rememberImagePainter(data = data.image)
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = data.name!!,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            maxLines = 1
        )
    }
}