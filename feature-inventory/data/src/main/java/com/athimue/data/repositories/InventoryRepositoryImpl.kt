package com.athimue.data.repositories

import com.athimue.data.database.dao.InventoryDao
import com.athimue.data.database.entity.InventoryEntity
import com.athimue.data.database.entity.toInventoryItem
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.repositories.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InventoryRepositoryImpl @Inject constructor(
    private val inventoryDao: InventoryDao
) : InventoryRepository {

    override suspend fun addInventory(
        inventoryItem: InventoryItem
    ) {
        inventoryDao.insert(
            InventoryEntity(
                name = inventoryItem.name,
                picture = inventoryItem.picture,
                size = inventoryItem.size,
                quantity = 1,
                buyPrice = inventoryItem.buyPrice,
                buyDate = inventoryItem.buyDate,
                buyPlace = inventoryItem.buyPlace,
            )
        )
    }

    override suspend fun deleteInventory(inventoryItemId: Long) {
        inventoryDao.delete(inventoryItemId)
    }

    override fun getInventory(id: Long): Flow<InventoryItem> =
        inventoryDao.getById(id).map { it.toInventoryItem() }

    override fun getInventory(): Flow<List<InventoryItem>> =
        inventoryDao.getAll().map { it.map { it.toInventoryItem() } }
}