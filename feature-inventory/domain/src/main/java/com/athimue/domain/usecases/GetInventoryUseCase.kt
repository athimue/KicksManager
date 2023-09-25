package com.athimue.domain.usecases

import com.athimue.domain.models.InventoryItem
import kotlinx.coroutines.flow.Flow

interface GetInventoryUseCase {
    fun invoke(): Flow<List<InventoryItem>>
}