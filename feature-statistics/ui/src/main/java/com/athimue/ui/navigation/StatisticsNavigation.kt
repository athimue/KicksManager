package com.athimue.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.athimue.ui.composable.sellplace.SellPlaceStatisticsComposable
import com.athimue.ui.composable.statistics.StatisticsComposable

@Composable
fun StatisticsNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StatisticsRoutes.Home,
    ) {
        composable<StatisticsRoutes.Home> {
            StatisticsComposable(onClick = { sellPlace ->
                navController.navigate(StatisticsRoutes.SellPlace(place = sellPlace))
            })
        }
        composable<StatisticsRoutes.SellPlace> {
            val args = it.toRoute<StatisticsRoutes.SellPlace>()
            SellPlaceStatisticsComposable(
                sellPlace = args.place,
                onBack = { navController.navigateUp() },
            )
        }
    }
}
