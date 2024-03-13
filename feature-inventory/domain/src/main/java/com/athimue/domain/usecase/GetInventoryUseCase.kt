package com.athimue.domain.usecase

import com.athimue.domain.model.InventoryItem
import com.athimue.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryUseCase
    @Inject
    constructor(
        private val inventoryRepository: InventoryRepository,
    ) {
        operator fun invoke(): Flow<List<InventoryItem>> = inventoryRepository.getInventory()
    }
