package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem

interface AddSellUseCase {
    suspend fun invoke(
        inventoryItemId: Long,
        sellPrice: String,
        sellDate: String,
        sellPlace: String
    )
}