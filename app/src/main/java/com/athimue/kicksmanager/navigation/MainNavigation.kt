package com.athimue.kicksmanager.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.athimue.ui.InventoryComposable

sealed class Screen(val route: String) {
    object Inventory : Screen("inventory")
    object Statistics : Screen("statistics")
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomBar(
                currentRoute = navController.currentDestination?.route
            )
        }
    ) {
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = Screen.Inventory.route
        ) {
            composable(Screen.Inventory.route) {
                InventoryComposable()
            }
            composable(Screen.Statistics.route) {
                InventoryComposable()
            }
        }
    }
}

@Composable
fun BottomBar(
    currentRoute: String?
) {
    NavigationBar() {
        NavigationBarItem(
            selected = currentRoute == Screen.Inventory.route,
            icon = { Icon(imageVector = Icons.Rounded.AccountBox, contentDescription = "") },
            label = { Text("Inventory") },
            onClick = { /*TODO*/ })
        NavigationBarItem(
            selected = currentRoute == Screen.Statistics.route,
            icon = { Icon(imageVector = Icons.Rounded.Check, contentDescription = "")},
            label = { Text("Statistics") },
            onClick = { /*TODO*/ })
    }
}