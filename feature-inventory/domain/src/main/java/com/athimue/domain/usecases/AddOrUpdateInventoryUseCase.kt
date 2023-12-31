package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem

interface AddOrUpdateInventoryUseCase {
    suspend fun invoke(inventoryItem: InventoryItem)
}