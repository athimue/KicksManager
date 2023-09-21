package com.athimue.domain.models

data class Sell(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: String,
    val buyPrice: String,
    val sellPrice: String,
    val sellDate: String,
    val sellPlace: String,
)
