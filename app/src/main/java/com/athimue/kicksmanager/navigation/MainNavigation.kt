package com.athimue.kicksmanager.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.athimue.ui.composables.inventory.InventoryComposable
import com.athimue.ui.composables.sells.SellsComposable
import com.athimue.ui.navigation.StatisticsNavigation
import com.athimue.ui.navigation.TrendsNavigation

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(
                currentRoute = navController.currentDestination?.route,
                onInventoryClick = { navController.navigate(Routes.Inventory) },
                onSellsClick = { navController.navigate(Routes.Sells) },
                onStatisticsClick = { navController.navigate(Routes.Statistics) },
                onTrendsClick = { navController.navigate(Routes.Trends) }
            )
        },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
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

@Composable
fun BottomBar(
    currentRoute: String?,
    onInventoryClick: () -> Unit,
    onSellsClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    onTrendsClick: () -> Unit,
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Routes.Inventory.toString(),
            icon = { Icon(imageVector = Icons.AutoMirrored.Rounded.List, contentDescription = "") },
            label = { Text("Inventory") },
            onClick = onInventoryClick,
            colors =
                NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
        )
        NavigationBarItem(
            selected = currentRoute == Routes.Sells.toString(),
            icon = { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = "") },
            label = { Text("Sells") },
            onClick = onSellsClick,
            colors =
                NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
        )
        NavigationBarItem(
            selected = currentRoute == Routes.Statistics.toString(),
            icon = { Icon(imageVector = Icons.Rounded.Build, contentDescription = "") },
            label = { Text("Statistics") },
            onClick = onStatisticsClick,
            colors =
                NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
        )
        NavigationBarItem(
            selected = currentRoute == Routes.Trends.toString(),
            icon = { Icon(imageVector = Icons.Rounded.Star, contentDescription = null) },
            label = { Text("Trends") },
            onClick = onTrendsClick,
            colors =
                NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
        )
    }
}
