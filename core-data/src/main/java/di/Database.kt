package di

import androidx.room.RoomDatabase
import database.dao.SellDao
import database.entity.SellEntity

@androidx.room.Database(
    entities = [SellEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun sellDao(): SellDao

    companion object {
        const val DATABASE_NAME: String = "CoreDatabase.db"
    }
}