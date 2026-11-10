package com.athimue.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.athimue.core_ui.R

/**
 * Icônes Material Symbols Rounded (le jeu d'icônes actuel de Google), embarquées en vector drawables.
 * Utilisation : Icon(painter = KicksIcons.Add, contentDescription = …)
 */
object KicksIcons {
    val Add: Painter @Composable get() = painterResource(R.drawable.ks_add)
    val ArrowBack: Painter @Composable get() = painterResource(R.drawable.ks_arrow_back)
    val Calendar: Painter @Composable get() = painterResource(R.drawable.ks_calendar_month)
    val Close: Painter @Composable get() = painterResource(R.drawable.ks_close)
    val Delete: Painter @Composable get() = painterResource(R.drawable.ks_delete)
    val ArrowDown: Painter @Composable get() = painterResource(R.drawable.ks_keyboard_arrow_down)
    val ArrowUp: Painter @Composable get() = painterResource(R.drawable.ks_keyboard_arrow_up)
    val Payments: Painter @Composable get() = painterResource(R.drawable.ks_payments)
    val Search: Painter @Composable get() = painterResource(R.drawable.ks_search)

    // Onglets : version contour + version pleine pour l'onglet sélectionné
    val Inventory: Painter @Composable get() = painterResource(R.drawable.ks_inventory_2)
    val InventoryFilled: Painter @Composable get() = painterResource(R.drawable.ks_inventory_2_filled)
    val Sell: Painter @Composable get() = painterResource(R.drawable.ks_sell)
    val SellFilled: Painter @Composable get() = painterResource(R.drawable.ks_sell_filled)
    val Stats: Painter @Composable get() = painterResource(R.drawable.ks_monitoring)
    val StatsFilled: Painter @Composable get() = painterResource(R.drawable.ks_monitoring_filled)
    val Hype: Painter @Composable get() = painterResource(R.drawable.ks_local_fire_department)
    val HypeFilled: Painter @Composable get() = painterResource(R.drawable.ks_local_fire_department_filled)
}
