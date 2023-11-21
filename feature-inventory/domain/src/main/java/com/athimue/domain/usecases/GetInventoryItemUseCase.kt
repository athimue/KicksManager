package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import kotlinx.coroutines.flow.Flow

interface GetInventoryItemUseCase {
    suspend fun invoke(itemId: Long): Flow<InventoryItem>
}