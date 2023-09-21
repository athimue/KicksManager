package com.athimue.domain.repositories

import com.athimue.domain.models.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    suspend fun addInventory(inventoryItem: InventoryItem)

    suspend fun deleteInventory(inventoryItemId: Long)

    fun getInventory(): Flow<List<InventoryItem>>
}