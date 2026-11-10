package com.athimue.domain.models

/** Résumé du stock actuel : nombre de paires et valeur d'achat totale. */
data class StockSummary(
    val pairs: Int,
    val value: Double,
)
