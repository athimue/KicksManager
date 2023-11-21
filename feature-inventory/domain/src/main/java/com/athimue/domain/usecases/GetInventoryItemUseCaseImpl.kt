package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInventoryItemUseCaseImpl @Inject constructor(private val inventoryRepository: InventoryRepository) :
    GetInventoryItemUseCase {

    override suspend fun invoke(itemId: Long): Flow<InventoryItem> =
        inventoryRepository.getInventory(itemId)

}