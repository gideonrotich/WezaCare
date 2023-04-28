/*
 * Copyright 2022 Gideon Rotich.
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
package com.swayy.wezacare

import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.swayy.wezacare.navigation.NavGraphs

sealed class BottomNavItem(var title: String, var icon: Int, var screen: NavGraphSpec) {
    object Home : BottomNavItem(
        title = "Home",
        icon = com.swayy.core.R.drawable.baseline_home_24,
        screen = NavGraphs.home
    )

    object Favourite : BottomNavItem(
        title = "Favourite",
        icon = R.drawable.baseline_favorite_24,
        screen = NavGraphs.favourite
    )
}
