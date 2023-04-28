package com.swayy.favourite.presentation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

interface FavouriteNavigator {

    fun openFavourite()

    fun popBackStack()
}

@Destination
@Composable
fun FavouriteScreen(){

}