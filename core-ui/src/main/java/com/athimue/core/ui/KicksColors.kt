package com.athimue.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Couleurs « succès » (vente, bénéfice) qui n'existent pas dans le ColorScheme Material. */
object KicksColors {
    val success: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFF81C784) else Color(0xFF2E7D32)

    val successContainer: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFF1B5E20) else Color(0xFFC8E6C9)

    val onSuccessContainer: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFFC8E6C9) else Color(0xFF1B5E20)
}
