package com.athimue.domain.usecases

interface DeleteInventoryUseCase {
    suspend fun invoke(inventoryItemId: Long)
}