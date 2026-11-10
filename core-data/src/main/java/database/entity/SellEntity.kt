package database.entity

import androidx.room.ColumnInfo
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
    val sellDate: String,
    /** Frais liés à la vente (commission de la plateforme, port…). Ajouté en version 2 de la base. */
    @ColumnInfo(defaultValue = "0") val fees: Double = 0.0,
)
