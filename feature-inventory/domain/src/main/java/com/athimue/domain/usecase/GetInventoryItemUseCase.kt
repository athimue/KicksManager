package com.athimue.domain.usecase

import com.athimue.domain.model.InventoryItem
import com.athimue.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryItemUseCase
    @Inject
    constructor(private val inventoryRepository: InventoryRepository) {
        operator fun invoke(itemId: Long): Flow<InventoryItem> = inventoryRepository.getInventory(itemId)
    }
