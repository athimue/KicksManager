package com.athimue.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.athimue.ui.composable.detail.DetailComposable
import com.athimue.ui.composable.detail.DetailModel
import com.athimue.ui.composable.trends.TrendsComposable
import com.google.gson.Gson

@Composable
fun TrendsNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TrendsRoutes.Home,
    ) {
        composable<TrendsRoutes.Home> {
            TrendsComposable(
                goToDetail = { item ->
                    navController.navigate(TrendsRoutes.Detail(item = Gson().toJson(item)))
                },
            )
        }
        composable<TrendsRoutes.Detail> {
            val args = it.toRoute<TrendsRoutes.Detail>()
            DetailComposable(
                detail = Gson().fromJson(args.item, DetailModel::class.java),
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
