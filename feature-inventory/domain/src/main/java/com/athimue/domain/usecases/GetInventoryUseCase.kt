package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import kotlinx.coroutines.flow.Flow

interface GetInventoryUseCase {
    suspend fun invoke(): Flow<List<InventoryItem>>
}