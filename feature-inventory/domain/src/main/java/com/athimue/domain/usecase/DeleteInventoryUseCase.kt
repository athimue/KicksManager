package com.athimue.domain.usecase

import com.athimue.domain.repository.InventoryRepository
import javax.inject.Inject

class DeleteInventoryUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(inventoryItemId: Long) {
            inventoryRepository.deleteInventory(inventoryItemId)
        }
    }
