package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository
) {
    fun invoke(): Flow<List<InventoryItem>> =
        inventoryRepository.getInventory()
}