package com.athimue.ui.composables.inventory

import com.athimue.ui.composables.uimodels.InventoryUiModel

data class InventoryUiState(
    var inventory: List<InventoryUiModel> = listOf()
)