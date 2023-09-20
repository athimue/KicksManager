package com.athimue.ui.composables.inventory

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.models.Sneaker

data class InventoryUiState(
    var inventory: List<InventoryItem>? = null,
    var sneakerResult: List<Sneaker>? = null
)