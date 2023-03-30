package com.swayy.core_network

import com.swayy.core_network.Constants.GET_CHARACTERS
import com.swayy.core_network.model.harrypotter.HarrypotterResponseDto
import retrofit2.http.GET

interface HarryPotterApi {
    @GET(GET_CHARACTERS)
    suspend fun getCharacters(): HarrypotterResponseDto
}