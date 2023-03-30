package com.swayy.core.domain

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val themeStream: Flow<Int>
    suspend fun setTheme(themeValue: Int)
}
