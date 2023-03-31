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
package com.swayy.core_database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swayy.core_network.model.harrypotter.Wand

@ProvidedTypeConverter
class Converters(private val gson: Gson) {

    @TypeConverter
    fun listToJsonString(value: List<String>?): String? = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String?) =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromContributor(str: Wand): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun toContributor(str: String): Wand {
        return Gson().fromJson(str, object : TypeToken<Wand>() {}.type)
    }
}
