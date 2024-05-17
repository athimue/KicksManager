package com.athimue.kicksmanager.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    object Inventory

    @Serializable
    object Sells

    @Serializable
    object Statistics

    @Serializable
    object Trends
}