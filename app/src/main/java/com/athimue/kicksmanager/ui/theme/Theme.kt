package com.athimue.kicksmanager.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val KicksShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

private val LightColors = lightColorScheme(
    primary = IndigoPrimary,
    onPrimary = IndigoOnPrimary,
    primaryContainer = IndigoPrimaryContainer,
    onPrimaryContainer = IndigoOnPrimaryContainer,
    inversePrimary = NightPrimary,
    secondary = IndigoSecondary,
    onSecondary = Color.White,
    secondaryContainer = IndigoSecondaryContainer,
    onSecondaryContainer = IndigoOnSecondaryContainer,
    tertiary = IndigoTertiary,
    onTertiary = Color.White,
    tertiaryContainer = IndigoTertiaryContainer,
    onTertiaryContainer = IndigoOnTertiaryContainer,
    background = LavenderBackground,
    onBackground = InkOnBackground,
    surface = LavenderBackground,
    onSurface = InkOnBackground,
    surfaceVariant = LavenderSurfaceVariant,
    onSurfaceVariant = InkOnSurfaceVariant,
    surfaceTint = IndigoPrimary,
    inverseSurface = Color(0xFF302D4D),
    inverseOnSurface = Color(0xFFF3EFFF),
    outline = LavenderOutline,
    outlineVariant = LavenderOutlineVariant,
    surfaceBright = LavenderBackground,
    surfaceDim = Color(0xFFD9D7E8),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color.White,
    surfaceContainer = LavenderContainer,
    surfaceContainerHigh = LavenderContainerHigh,
    surfaceContainerHighest = LavenderContainerHighest,
)

private val DarkColors = darkColorScheme(
    primary = NightPrimary,
    onPrimary = NightOnPrimary,
    primaryContainer = NightPrimaryContainer,
    onPrimaryContainer = NightOnPrimaryContainer,
    inversePrimary = IndigoPrimary,
    secondary = NightSecondary,
    onSecondary = Color(0xFF2F2D4C),
    secondaryContainer = NightSecondaryContainer,
    onSecondaryContainer = NightOnSecondaryContainer,
    tertiary = NightTertiary,
    onTertiary = Color(0xFF5E1139),
    tertiaryContainer = NightTertiaryContainer,
    onTertiaryContainer = NightOnTertiaryContainer,
    background = NightBackground,
    onBackground = NightOnBackground,
    surface = NightBackground,
    onSurface = NightOnBackground,
    surfaceVariant = NightSurfaceVariant,
    onSurfaceVariant = NightOnSurfaceVariant,
    surfaceTint = NightPrimary,
    inverseSurface = NightOnBackground,
    inverseOnSurface = Color(0xFF302D4D),
    outline = NightOutline,
    outlineVariant = NightSurfaceVariant,
    surfaceBright = Color(0xFF39384B),
    surfaceDim = NightBackground,
    surfaceContainerLowest = NightContainerLow,
    surfaceContainerLow = NightContainerLow,
    surfaceContainer = NightContainer,
    surfaceContainerHigh = NightContainerHigh,
    surfaceContainerHighest = NightContainerHighest,
)

@Composable
fun KicksManagerTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    // Palette de l'app imposée par défaut. Passer à true pour suivre le fond d'écran (Material You, Android 12+).
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = KicksTypography,
        shapes = KicksShapes,
        content = content,
    )
}
