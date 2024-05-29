package com.athimue.ui.navigation

import com.athimue.ui.composable.detail.DetailModel
import kotlinx.serialization.Serializable

@Serializable
sealed class TrendsRoutes {
    @Serializable
    object Home

    @Serializable
    data class Detail(
        val item : String
    )
}
