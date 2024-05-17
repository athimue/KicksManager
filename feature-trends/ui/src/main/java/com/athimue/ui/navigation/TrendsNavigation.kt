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
        startDestination = Routes.Home,
    ) {
        composable<Routes.Home> {
            StatisticsComposable(onClick = { sellPlace ->
                navController.navigate(Routes.SellPlace(place = sellPlace))
            })
        }
        composable<Routes.SellPlace> {
            val args = it.toRoute<Routes.SellPlace>()
            SellPlaceStatisticsComposable(
                sellPlace = args.place,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
