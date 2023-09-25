package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem

interface AddInventoryUseCase {
    suspend fun invoke(inventoryItem: InventoryItem)
}