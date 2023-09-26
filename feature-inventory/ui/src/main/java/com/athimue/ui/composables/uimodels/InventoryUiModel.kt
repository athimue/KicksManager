package com.athimue.ui.composables.uimodels

import com.athimue.domain.models.InventoryItem

data class InventoryUiModel(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
)

fun InventoryItem.toInventoryUiModel() = InventoryUiModel(
    id = id,
    name = name,
    picture = picture,
    size = size,
    quantity = quantity,
    buyPrice = buyPrice,
)
