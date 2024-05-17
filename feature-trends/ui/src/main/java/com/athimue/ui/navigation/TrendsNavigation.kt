package com.athimue.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.athimue.ui.composable.trends.TrendsComposable

@Composable
fun TrendsNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TrendsRoutes.Home,
    ) {
        composable<TrendsRoutes.Home> {
            TrendsComposable()
        }
    }
}
