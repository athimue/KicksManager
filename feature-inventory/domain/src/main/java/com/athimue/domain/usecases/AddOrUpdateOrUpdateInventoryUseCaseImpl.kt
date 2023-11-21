package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import javax.inject.Inject

class AddOrUpdateOrUpdateInventoryUseCaseImpl @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : AddOrUpdateInventoryUseCase {
    override suspend fun invoke(inventoryItem: InventoryItem) =
        inventoryRepository.addOrUpdateInventory(inventoryItem)
}