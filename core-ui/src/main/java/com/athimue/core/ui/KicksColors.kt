package com.athimue.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Couleurs propres à KicksManager, en plus du ColorScheme Material. */
object KicksColors {
    /** Fond des en-têtes colorés (indigo vif en clair, indigo profond en sombre). */
    val header: Color
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.primary
        }

    val onHeader: Color
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onPrimary
        }

    val success: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFF6EE7B7) else Color(0xFF047857)

    val successContainer: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFF064E3B) else Color(0xFFD1FAE5)

    val onSuccessContainer: Color
        @Composable get() = if (isSystemInDarkTheme()) Color(0xFFD1FAE5) else Color(0xFF064E3B)
}
