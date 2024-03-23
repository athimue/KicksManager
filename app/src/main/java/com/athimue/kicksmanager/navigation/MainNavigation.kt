package com.athimue.kicksmanager.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.ShoppingCart
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

sealed class Screen(val route: String) {
    data object Inventory : Screen("inventory")

    data object Sells : Screen("sells")

    data object Statistics : Screen("statistics")
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(
                currentRoute = navController.currentDestination?.route,
                onInventoryClick = { navController.navigate(Screen.Inventory.route) },
                onSellsClick = { navController.navigate(Screen.Sells.route) },
                onStatisticsClick = { navController.navigate(Screen.Statistics.route) },
            )
        },
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Inventory.route,
        ) {
            composable(Screen.Inventory.route) {
                InventoryComposable()
            }
            composable(Screen.Sells.route) {
                SellsComposable()
            }
            composable(Screen.Statistics.route) {
                StatisticsNavigation()
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
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.Inventory.route,
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
            selected = currentRoute == Screen.Sells.route,
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
            selected = currentRoute == Screen.Statistics.route,
            icon = { Icon(imageVector = Icons.Rounded.Build, contentDescription = "") },
            label = { Text("Statistics") },
            onClick = onStatisticsClick,
            colors =
                NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                ),
        )
    }
}
