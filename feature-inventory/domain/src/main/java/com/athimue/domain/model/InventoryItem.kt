package com.athimue.domain.model

data class InventoryItem(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
    val buyDate: String,
    val buyPlace: String,
)
