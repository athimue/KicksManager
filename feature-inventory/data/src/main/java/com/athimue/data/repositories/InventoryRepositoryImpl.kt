package com.athimue.data.repositories

import com.athimue.data.database.dao.InventoryDao
import com.athimue.data.database.entity.toInventoryItem
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val inventoryDao: InventoryDao
) : InventoryRepository {
    override suspend fun getInventory(): Flow<List<InventoryItem>> =
        inventoryDao.getAll().map { it.map { it.toInventoryItem() } }
}