package com.athimue.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.athimue.domain.model.InventoryItem

@Entity(tableName = "inventory")
data class InventoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
    val buyDate: String,
    val buyPlace: String,
)

fun InventoryEntity.toInventoryItem() = InventoryItem(
    id = id,
    name = name,
    picture = picture,
    size = size,
    quantity = quantity,
    buyPrice = buyPrice,
    buyDate = buyDate,
    buyPlace = buyPlace,
)
