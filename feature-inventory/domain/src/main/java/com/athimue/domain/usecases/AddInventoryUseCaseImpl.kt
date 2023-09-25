package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import javax.inject.Inject

class AddInventoryUseCaseImpl @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : AddInventoryUseCase {
    override suspend fun invoke(inventoryItem: InventoryItem) =
        inventoryRepository.addInventory(inventoryItem)
}