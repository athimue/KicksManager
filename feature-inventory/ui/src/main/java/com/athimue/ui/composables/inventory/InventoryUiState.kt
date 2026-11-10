package com.athimue.ui.composables.inventory

import com.athimue.domain.model.InventorySort

data class InventoryUiState(
    /** Paires affichées (après recherche et tri). */
    val inventory: List<InventoryUiModel> = listOf(),
    /** Totaux sur tout le stock, indépendamment de la recherche. */
    val totalPairs: Int = 0,
    val totalValue: Double = 0.0,
    val query: String = "",
    val sort: InventorySort = InventorySort.RECENT,
)
