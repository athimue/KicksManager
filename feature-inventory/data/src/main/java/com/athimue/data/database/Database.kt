package com.athimue.data.database

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.athimue.data.database.dao.InventoryDao
import com.athimue.data.database.entity.InventoryEntity

@androidx.room.Database(
    entities = [InventoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
abstract class Database : RoomDatabase() {

    abstract fun inventoryDao(): InventoryDao

    companion object {
        const val DATABASE_NAME: String = "database.db"
    }
}