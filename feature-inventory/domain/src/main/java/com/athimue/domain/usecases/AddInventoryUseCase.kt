package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import javax.inject.Inject

class AddInventoryUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(inventoryItem: InventoryItem) =
        inventoryRepository.addInventory(inventoryItem)
}