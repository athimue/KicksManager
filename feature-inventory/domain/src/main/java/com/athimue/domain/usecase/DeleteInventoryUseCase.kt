package com.athimue.domain.usecase

import com.athimue.domain.model.InventoryItem
import com.athimue.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteInventoryUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        /** Supprime la paire et la renvoie, pour pouvoir annuler la suppression. */
        suspend operator fun invoke(inventoryItemId: Long): InventoryItem? {
            val deletedItem = runCatching { inventoryRepository.getInventory(inventoryItemId).first() }.getOrNull()
            inventoryRepository.deleteInventory(inventoryItemId)
            return deletedItem
        }
    }
