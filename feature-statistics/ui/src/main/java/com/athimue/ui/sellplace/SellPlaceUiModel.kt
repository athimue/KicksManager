package com.athimue.ui.sellplace

import com.athimue.domain.models.StatisticsSell

data class SellPlaceUiModel(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val profit: Double,
    val sellDate: String,
)

fun StatisticsSell.toSellPlaceUiModel() = SellPlaceUiModel(
    id = id,
    name = name,
    picture = picture,
    size = size,
    profit = sellPrice - buyPrice,
    sellDate = sellDate
)
