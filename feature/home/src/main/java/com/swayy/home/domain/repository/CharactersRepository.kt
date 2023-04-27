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
package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.core.work.NetworkChangeList
import com.swayy.core.work.Syncable
import com.swayy.home.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository: Syncable {

    fun getCharacters(): Flow<Resource<List<CharacterModel>>>
}