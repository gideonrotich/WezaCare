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
package com.swayy.wezacare.navigation

import androidx.navigation.NavController
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.swayy.favourite.presentation.FavouriteNavigator
import com.swayy.favourite.presentation.destinations.FavouriteScreenDestination
import com.swayy.home.presentation.destinations.DetailsScreenDestination
import com.swayy.home.presentation.destinations.HomeScreenDestination
import com.swayy.home.presentation.home.HomeNavigator

class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : HomeNavigator, FavouriteNavigator {

    override fun openHome() {
        navController.navigate(HomeScreenDestination within navGraph)
    }

    override fun openFavourite() {
        navController.navigate(FavouriteScreenDestination within navGraph)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun openCharacterDetails(characterId: String) {
        navController.navigate(DetailsScreenDestination(characterId = characterId) within navGraph)
    }
}
