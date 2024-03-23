package com.athimue.ui.composables.inventory

import com.athimue.domain.model.InventoryItem

data class InventoryUiModel(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
)

fun InventoryItem.toInventoryUiModel() =
    InventoryUiModel(
        id = id,
        name = name,
        picture = picture,
        size = size,
        quantity = quantity,
        buyPrice = buyPrice,
    )
