package com.athimue.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.athimue.ui.sellplace.SellPlaceStatisticsComposable
import com.athimue.ui.statistics.StatisticsComposable

sealed class StatisticsScreen(val route: String) {
    object Home : StatisticsScreen("home")
    object SellPlace : StatisticsScreen("sellplace/{place}")
}

@Composable
fun StatisticsNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = StatisticsScreen.Home.route
    ) {
        composable(route = StatisticsScreen.Home.route) {
            StatisticsComposable(onClick = { sellPlace ->
                navController.navigate(
                    StatisticsScreen.SellPlace.route.replace(
                        oldValue = "{place}", newValue = sellPlace
                    )
                )
            })
        }
        composable(
            route = StatisticsScreen.SellPlace.route, arguments = listOf(navArgument("place") {
                type = NavType.StringType
                defaultValue = "wethenew"
            })
        ) { sellPlace ->
            SellPlaceStatisticsComposable(sellPlace = sellPlace.arguments?.getString("place") ?: "",
                onBack = { navController.popBackStack() })
        }
    }
}