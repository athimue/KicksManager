package com.athimue.ui.composables.sells

import com.athimue.domain.models.Sell

data class SellUiModel(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val buyPrice: Double,
    val sellPrice: Double,
    val sellDate: String,
    val sellPlace: String,
    val fees: Double = 0.0,
) {
    val netProfit: Double get() = sellPrice - buyPrice - fees
}

fun Sell.toSellUiModel() = SellUiModel(
    id = id,
    name = name,
    picture = picture,
    size = size,
    buyPrice = buyPrice,
    sellPrice = sellPrice,
    sellDate = sellDate,
    sellPlace = sellPlace,
    fees = fees,
)
