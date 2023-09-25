package com.athimue.domain.usecases

import com.athimue.domain.repositories.InventoryRepository
import javax.inject.Inject

class DeleteInventoryUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    suspend fun invoke(inventoryItemId: Long) {
        inventoryRepository.deleteInventory(inventoryItemId)
    }
}