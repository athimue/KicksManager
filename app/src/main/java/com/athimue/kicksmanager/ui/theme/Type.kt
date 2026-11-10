package com.athimue.kicksmanager.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.DeviceFontFamilyName
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Police condensée du système (Roboto Condensed sur la plupart des appareils) pour les titres :
// look plus « sneaker / sport », sans embarquer de fichier de police.
private val Condensed = FontFamily(
    Font(DeviceFontFamilyName("sans-serif-condensed"), weight = FontWeight.Normal),
    Font(DeviceFontFamilyName("sans-serif-condensed"), weight = FontWeight.Bold),
)

private val Default = Typography()

private fun TextStyle.condensedBold() = copy(fontFamily = Condensed, fontWeight = FontWeight.Bold)

val KicksTypography = Typography(
    displayLarge = Default.displayLarge.condensedBold(),
    displayMedium = Default.displayMedium.condensedBold(),
    displaySmall = Default.displaySmall.condensedBold(),
    headlineLarge = Default.headlineLarge.condensedBold(),
    headlineMedium = Default.headlineMedium.condensedBold(),
    headlineSmall = Default.headlineSmall.condensedBold(),
    titleLarge = Default.titleLarge.condensedBold(),
)
