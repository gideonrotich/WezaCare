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
package com.swayy.core_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.core_network.model.harrypotter.Wand

@Entity(tableName = "characters_table")
data class CharacterEntity(
    val actor: String?,
    val alive: Boolean?,
    val alternate_actors: List<String>?,
    val alternate_names: List<String>?,
    val ancestry: String?,
    val dateOfBirth: String?,
    val eyeColour: String?,
    val gender: String?,
    val hairColour: String?,
    val hogwartsStaff: Boolean?,
    val hogwartsStudent: Boolean?,
    val house: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String?,
    val name: String?,
    val patronus: String?,
    val species: String?,
    val wand: Wand?,
    val wizard: Boolean?,
    val yearOfBirth: Int?
)
