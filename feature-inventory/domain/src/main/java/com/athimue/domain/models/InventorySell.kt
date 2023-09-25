package com.athimue.domain.models

data class InventorySell(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
    val sellPrice: Double,
    val sellDate: String,
    val sellPlace: String,
)
