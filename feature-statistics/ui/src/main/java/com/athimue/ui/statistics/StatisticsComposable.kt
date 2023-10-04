package com.athimue.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.ui.common.KeyFigureCard
import com.athimue.ui.common.KeyFigureTitle
import com.athimue.ui.statistics.StatisticsViewModel

@Composable
fun StatisticsComposable(
    viewModel: StatisticsViewModel = hiltViewModel(), onClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        KeyFigureTitle(title = "KEY FIGURES")
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard("?", "SHOES", 0xFFdf2098)
            KeyFigureCard(uiState.sells.toString(), "SELLS", 0xFF149414)
        }
        KeyFigureTitle(title = "PROFITS")
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard(uiState.sales.toString() + " €", "SALES", 0xFF708090)
            KeyFigureCard(
                uiState.expenses.toString() + " €", "EXPENSES", 0xFFda70d6
            )
            KeyFigureCard(uiState.margin.toString() + " €", "MARGIN", 0xFF149414)
        }
        KeyFigureTitle(title = "SALE PLACE")
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard(
                uiState.vintedSells.toString() + " €", "VINTED", 0xFF027782
            ) { onClick(it) }
            KeyFigureCard(
                uiState.wtnSells.toString() + " €", "WETHENEW", 0xFFed9613
            ) { onClick(it) }
            KeyFigureCard(uiState.goatSells.toString() + " €", "GOAT", 0xFF7857ff) { onClick(it) }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard("0 €", "DISCORD", 0xFF5765f2) {}
            KeyFigureCard(uiState.beforeShopSells.toString() + " €", "BEFORE SHOP", 0xFFdbff00) {}
            KeyFigureCard(uiState.meetupSells.toString() + " €", "MEET UP", 0xFFe0115f) {}
        }
    }
}