package com.athimue.domain.models

data class Sell(
    val id: Long,
    val name: String,
    val picture: String,
    val size: String,
    val quantity: Int,
    val buyPrice: Double,
    val sellPrice: Double,
    val sellDate: String,
    val sellPlace: String,
    val fees: Double = 0.0,
) {
    /** Bénéfice net : prix de vente - prix d'achat - frais. */
    val netProfit: Double get() = sellPrice - buyPrice - fees
}
