package com.athimue.domain.repositories

import com.athimue.domain.models.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    suspend fun addOrUpdateInventory(inventoryItem: InventoryItem)

    suspend fun deleteInventory(inventoryItemId: Long)

    fun getInventory(id: Long): Flow<InventoryItem>

    fun getInventory(): Flow<List<InventoryItem>>
}