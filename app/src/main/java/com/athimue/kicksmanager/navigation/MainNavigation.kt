package com.athimue.kicksmanager.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.athimue.ui.composables.inventory.InventoryComposable
import com.athimue.ui.StatisticsComposable

sealed class Screen(val route: String) {
    object Inventory : Screen("inventory")
    object Sells : Screen("sells")
    object Statistics : Screen("statistics")
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
                onStatisticsClick = { navController.navigate(Screen.Statistics.route) }
            )
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Inventory.route
        ) {
            composable(Screen.Inventory.route) {
                InventoryComposable()
            }
            composable(Screen.Sells.route) {
                StatisticsComposable()
            }
            composable(Screen.Statistics.route) {
                StatisticsComposable()
            }
        }
    }
}

@Composable
fun BottomBar(
    currentRoute: String?,
    onInventoryClick: () -> Unit,
    onStatisticsClick: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.Inventory.route,
            icon = { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "") },
            label = { Text("Inventory") },
            onClick = onInventoryClick
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Sells.route,
            icon = { Icon(imageVector = Icons.Rounded.Check, contentDescription = "") },
            label = { Text("Sells") },
            onClick = onStatisticsClick
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Statistics.route,
            icon = { Icon(imageVector = Icons.Rounded.Check, contentDescription = "") },
            label = { Text("Statistics") },
            onClick = onStatisticsClick
        )
    }
}