package com.athimue.data.database.dao

import androidx.room.*
import com.athimue.data.database.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InventoryDao {

    @Upsert
    abstract suspend fun insertOrUpdate(inventoryEntity: InventoryEntity): Long

    @Query("DELETE FROM inventory WHERE id IS :inventoryEntityId")
    abstract suspend fun delete(inventoryEntityId: Long): Int

    @Query("SELECT * FROM inventory where id is :id")
    abstract fun getById(id: Long): Flow<InventoryEntity>

    @Query("SELECT * FROM inventory")
    abstract fun getAll(): Flow<List<InventoryEntity>>
}
