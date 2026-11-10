package com.athimue.kicksmanager.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.athimue.kicksmanager.R

// Poppins (Google Fonts, licence OFL) : police géométrique moderne, embarquée dans res/font
private val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

private val Default = Typography()

private fun TextStyle.poppins(weight: FontWeight? = null) =
    copy(fontFamily = Poppins, fontWeight = weight ?: fontWeight)

val KicksTypography = Typography(
    displayLarge = Default.displayLarge.poppins(FontWeight.Bold),
    displayMedium = Default.displayMedium.poppins(FontWeight.Bold),
    displaySmall = Default.displaySmall.poppins(FontWeight.Bold),
    headlineLarge = Default.headlineLarge.poppins(FontWeight.Bold),
    headlineMedium = Default.headlineMedium.poppins(FontWeight.Bold),
    headlineSmall = Default.headlineSmall.poppins(FontWeight.SemiBold),
    titleLarge = Default.titleLarge.poppins(FontWeight.SemiBold),
    titleMedium = Default.titleMedium.poppins(FontWeight.SemiBold),
    titleSmall = Default.titleSmall.poppins(FontWeight.Medium),
    bodyLarge = Default.bodyLarge.poppins(),
    bodyMedium = Default.bodyMedium.poppins(),
    bodySmall = Default.bodySmall.poppins(),
    labelLarge = Default.labelLarge.poppins(FontWeight.Medium),
    labelMedium = Default.labelMedium.poppins(FontWeight.Medium),
    labelSmall = Default.labelSmall.poppins(FontWeight.Medium),
)
