package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entity.SellEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SellDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(sellEntity: SellEntity): Long

    @Query("DELETE FROM sell WHERE id IS :sellEntityId")
    abstract suspend fun delete(sellEntityId: Long): Int

    @Query("SELECT * FROM sell")
    abstract fun getAll(): Flow<List<SellEntity>>
}