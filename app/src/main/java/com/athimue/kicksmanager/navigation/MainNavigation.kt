package com.athimue.kicksmanager.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.athimue.core.ui.KicksIcons
import com.athimue.kicksmanager.R
import com.athimue.ui.composables.inventory.InventoryComposable
import com.athimue.ui.composables.sells.SellsComposable
import com.athimue.ui.navigation.StatisticsNavigation
import com.athimue.ui.navigation.TrendsNavigation

private data class TopLevelRoute<T : Any>(
    val route: T,
    @StringRes val label: Int,
    val icon: @Composable () -> Painter,
    val selectedIcon: @Composable () -> Painter,
)

private val topLevelRoutes = listOf(
    TopLevelRoute(Routes.Inventory, R.string.nav_inventory, { KicksIcons.Inventory }, { KicksIcons.InventoryFilled }),
    TopLevelRoute(Routes.Sells, R.string.nav_sales, { KicksIcons.Sell }, { KicksIcons.SellFilled }),
    TopLevelRoute(Routes.Statistics, R.string.nav_statistics, { KicksIcons.Stats }, { KicksIcons.StatsFilled }),
    TopLevelRoute(Routes.Trends, R.string.nav_trends, { KicksIcons.Hype }, { KicksIcons.HypeFilled }),
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
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest) {
                topLevelRoutes.forEach { topLevelRoute ->
                    val selected = currentDestination?.hierarchy?.any {
                        it.hasRoute(topLevelRoute.route::class)
                    } == true
                    NavigationBarItem(
                        selected = selected,
                        icon = {
                            Icon(
                                painter = if (selected) topLevelRoute.selectedIcon() else topLevelRoute.icon(),
                                contentDescription = null,
                            )
                        },
                        label = { Text(text = stringResource(topLevelRoute.label)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
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
