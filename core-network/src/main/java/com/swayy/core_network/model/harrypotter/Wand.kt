package com.swayy.core_network.model.harrypotter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Wand(
    val core: String,
    val length: Double,
    val wood: String
)