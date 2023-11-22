package com.athimue.ui.composable.statistics

data class StatisticsUiState(
    var shoes: Int = 15,
    var sells: Int = -1,
    var expenses: Double = -1.0,
    var sales: Double = -1.0,
    var margin: Double = -1.0,
    var wtnSells: Double = -1.0,
    var vintedSells: Double = -1.0,
    var goatSells: Double = -1.0,
    var meetupSells: Double = -1.0,
    var beforeShopSells: Double = -1.0,
)