package com.athimue.ui.composable.statistics

data class StatisticsUiState(
    val stockPairs: Int? = null,
    val stockValue: Double? = null,
    val sells: Int = 0,
    val sales: Double = 0.0,
    val expenses: Double = 0.0,
    val fees: Double = 0.0,
    val margin: Double = 0.0,
    val platforms: List<PlatformStatistics> = listOf(),
)

/** Total des ventes pour une plateforme (clé telle qu'enregistrée avec la vente, ex. « VINTED »). */
data class PlatformStatistics(
    val key: String,
    val sales: Double,
    val count: Int,
)
