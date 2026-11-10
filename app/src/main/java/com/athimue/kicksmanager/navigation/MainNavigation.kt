package com.athimue.kicksmanager.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.athimue.kicksmanager.R
import com.athimue.ui.composables.inventory.InventoryComposable
import com.athimue.ui.composables.sells.SellsComposable
import com.athimue.ui.navigation.StatisticsNavigation
import com.athimue.ui.navigation.TrendsNavigation

private data class TopLevelRoute<T : Any>(
    val route: T,
    @StringRes val label: Int,
    val icon: @Composable () -> Painter,
)

private val topLevelRoutes = listOf(
    TopLevelRoute(Routes.Inventory, R.string.nav_inventory) { rememberVectorPainter(Icons.AutoMirrored.Rounded.List) },
    TopLevelRoute(Routes.Sells, R.string.nav_sales) { rememberVectorPainter(Icons.Rounded.ShoppingCart) },
    TopLevelRoute(Routes.Statistics, R.string.nav_statistics) { painterResource(R.drawable.ic_bar_chart) },
    TopLevelRoute(Routes.Trends, R.string.nav_trends) { painterResource(R.drawable.ic_trending_up) },
)

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        // Chaque écran gère sa propre barre de titre (et donc la barre d'état) :
        // ce Scaffold ne s'occupe que de la barre de navigation du bas.
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            NavigationBar {
                topLevelRoutes.forEach { topLevelRoute ->
                    val selected = currentDestination?.hierarchy?.any {
                        it.hasRoute(topLevelRoute.route::class)
                    } == true
                    NavigationBarItem(
                        selected = selected,
                        icon = { Icon(painter = topLevelRoute.icon(), contentDescription = null) },
                        label = { Text(text = stringResource(topLevelRoute.label)) },
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                // Un seul exemplaire de chaque onglet dans la pile, état conservé
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            navController = navController,
            startDestination = Routes.Inventory,
        ) {
            composable<Routes.Inventory> {
                InventoryComposable()
            }
            composable<Routes.Sells> {
                SellsComposable()
            }
            composable<Routes.Statistics> {
                StatisticsNavigation()
            }
            composable<Routes.Trends> {
                TrendsNavigation()
            }
        }
    }
}
