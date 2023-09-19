package com.athimue.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.athimue.data.database.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class InventoryDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(inventoryEntity: InventoryEntity): Long

    @Query("SELECT * FROM inventory")
    abstract fun getAll(): Flow<List<InventoryEntity>>
}
