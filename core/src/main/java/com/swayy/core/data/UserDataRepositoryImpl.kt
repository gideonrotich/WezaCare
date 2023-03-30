
package com.swayy.core.data

import com.swayy.core.domain.UserDataRepository
import kotlinx.coroutines.flow.Flow

class UserDataRepositoryImpl(
    private val mealTimePreferences: MealTimePreferences
) : UserDataRepository {
    override val themeStream: Flow<Int>
        get() = mealTimePreferences.getTheme

    override suspend fun setTheme(themeValue: Int) {
        mealTimePreferences.saveTheme(themeValue = themeValue)
    }
}
