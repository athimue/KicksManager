package com.athimue.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Home

    @Serializable
    data class SellPlace(
        val place: String,
    )
}
