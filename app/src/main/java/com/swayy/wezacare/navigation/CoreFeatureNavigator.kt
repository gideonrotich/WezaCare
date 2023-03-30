package com.swayy.wezacare.navigation

import android.net.Uri
import androidx.navigation.NavController
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.swayy.home.presentation.destinations.DetailsScreenDestination
import com.swayy.home.presentation.destinations.HomeScreenDestination
import com.swayy.home.presentation.home.HomeNavigator


class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : HomeNavigator {

    override fun openHome() {
        navController.navigate(HomeScreenDestination within navGraph)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun openCharacterDetails(characterId: String) {
        navController.navigate(DetailsScreenDestination(characterId = characterId) within navGraph)
    }

}
