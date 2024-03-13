package com.athimue.domain.usecase

import com.athimue.domain.model.InventoryItem
import com.athimue.domain.repository.InventoryRepository
import javax.inject.Inject

class AddOrUpdateInventoryUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        suspend operator fun invoke(inventoryItem: InventoryItem) = inventoryRepository.addOrUpdateInventory(inventoryItem)
    }
