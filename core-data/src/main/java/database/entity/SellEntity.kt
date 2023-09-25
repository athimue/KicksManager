package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sell")
data class SellEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
    val sellPrice: Double,
    val sellPlace: String,
    val sellDate: String
)