package com.swayy.wezacare

import androidx.lifecycle.ViewModel
import com.swayy.core.domain.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {

    val theme = userDataRepository.themeStream
}
