package com.athimue.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.ui.common.KeyFigureCard
import com.athimue.ui.common.KeyFigureTitle

@Composable
fun StatisticsComposable(
    viewModel: StatisticsViewModel = hiltViewModel()
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
            KeyFigureCard(uiState.expenses.toString() + " €", "EXPENSES", 0xFFda70d6)
            KeyFigureCard(uiState.margin.toString() + " €", "MARGIN", 0xFF149414)
        }
        KeyFigureTitle(title = "SALE PLACE")
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard(uiState.vintedSells.toString() + " €", "VINTED", 0xFF027782)
            KeyFigureCard(uiState.wtnSells.toString() + " €", "WETHENEW", 0xFFed9613)
            KeyFigureCard(uiState.goatSells.toString() + " €", "GOAT", 0xFF7857ff)
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            KeyFigureCard("0 €", "DISCORD", 0xFF5765f2)
            KeyFigureCard(uiState.beforeShopSells.toString() + " €", "BEFORE SHOP", 0xFFdbff00)
            KeyFigureCard(uiState.meetupSells.toString() + " €", "MEET UP", 0xFFe0115f)
        }
    }
}