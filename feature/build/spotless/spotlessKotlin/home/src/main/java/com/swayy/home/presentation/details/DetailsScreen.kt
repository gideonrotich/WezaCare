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
package com.swayy.home.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.swayy.core.R
import com.swayy.home.domain.model.CharacterModel
import com.swayy.home.presentation.home.CharactersViewModel
import com.swayy.home.presentation.home.HomeNavigator

@Destination
@Composable
fun DetailsScreen(
    characterId: String,
    viewModel: CharactersViewModel = hiltViewModel(),
    navigator: HomeNavigator,
) {
    val charactersState = viewModel.characters.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(charactersState.characters.filter { it.id == characterId }) { character ->
                Row(modifier = Modifier.padding(top = 6.dp)) {
                    Image(
                        modifier = Modifier
                            .size(34.dp)
                            .align(Alignment.CenterVertically)
                            .padding(start = 6.dp)
                            .clickable(onClick = {
                                navigator.popBackStack()
                            }),
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Character Details",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        maxLines = 1
                    )
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(50.dp))
                    val image: Painter =
                        rememberImagePainter(data = character.image)
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(1.dp, Color.White, RoundedCornerShape(100.dp))
                            .align(Alignment.CenterHorizontally),
                        painter = image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    character.name?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TableScreen(charactersState.characters, characterId)
                }
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

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.White)
            .weight(weight)
            .padding(8.dp),
        maxLines = 1
    )
}

@Composable
fun TableScreen(characterModel: List<CharacterModel>, characterId: String) {

    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .height(600.dp)
    ) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                TableCell(text = "Character", weight = column1Weight)
                TableCell(text = "Details", weight = column2Weight)
            }
        }
        // Here are all the lines of your table.
        items(characterModel.filter { it.id == characterId }) {

            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "name", weight = column1Weight)
                it.name?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "species", weight = column1Weight)
                it.species?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "gender", weight = column1Weight)
                it.gender?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "house", weight = column1Weight)
                it.house?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "dateOfBirth", weight = column1Weight)
                it.dateOfBirth?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "yearOfBirth", weight = column1Weight)
                TableCell(text = it.yearOfBirth.toString(), weight = column2Weight)
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "wizard", weight = column1Weight)
                TableCell(text = it.wizard.toString(), weight = column2Weight)
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "ancestry", weight = column1Weight)
                it.ancestry?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "eye colour", weight = column1Weight)
                it.eyeColour?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "hair colour", weight = column1Weight)
                it.hairColour?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "patronus", weight = column1Weight)
                it.patronus?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "hogwarts student", weight = column1Weight)
                TableCell(text = it.hogwartsStudent.toString(), weight = column2Weight)
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "hogwarts staff", weight = column1Weight)
                TableCell(text = it.hogwartsStaff.toString(), weight = column2Weight)
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "actor", weight = column1Weight)
                it.actor?.let { it1 -> TableCell(text = it1, weight = column2Weight) }
            }
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = "alive", weight = column1Weight)
                TableCell(text = it.alive.toString(), weight = column2Weight)
            }
        }
    }
}