package com.athimue.ui.composables.sells

import com.athimue.domain.models.Sell

data class SellUiState(
    var sells: List<Sell> = listOf()
) {
}