package com.athimue.ui.composables.uimodels

import com.athimue.domain.models.InventoryItem
import com.athimue.ui.composables.inventoryform.InventoryFormModalUiModel

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

fun InventoryItem.toInventoryFormModalUiModel() = InventoryFormModalUiModel(
    id = id,
    name = name,
    size = size,
    picture = picture,
    buyPrice = buyPrice,
    buyPlace = buyPlace,
    buyDate = buyDate,
)
