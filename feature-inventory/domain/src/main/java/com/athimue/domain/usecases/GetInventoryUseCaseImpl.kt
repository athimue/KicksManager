package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryUseCaseImpl @Inject constructor(
    private val inventoryRepository: InventoryRepository
) : GetInventoryUseCase {
    override suspend fun invoke(): Flow<List<InventoryItem>> =
        inventoryRepository.getInventory()
}