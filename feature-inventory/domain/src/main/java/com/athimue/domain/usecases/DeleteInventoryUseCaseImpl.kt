package com.athimue.domain.usecases

import com.athimue.domain.repositories.InventoryRepository
import javax.inject.Inject

class DeleteInventoryUseCaseImpl @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : DeleteInventoryUseCase {
    override suspend fun invoke(inventoryItemId: Long) {
        inventoryRepository.deleteInventory(inventoryItemId)
    }
}