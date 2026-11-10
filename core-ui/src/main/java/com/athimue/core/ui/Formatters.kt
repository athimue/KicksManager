package com.athimue.core.ui

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/** 120.0 -> « 120 € », 89.9 -> « 89,90 € » (selon la langue du téléphone). */
fun formatPrice(amount: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
    format.currency = Currency.getInstance("EUR")
    val digits = if (amount % 1.0 == 0.0) 0 else 2
    format.minimumFractionDigits = digits
    format.maximumFractionDigits = digits
    return format.format(amount)
}

/** Comme [formatPrice], avec un « + » devant les montants positifs (utile pour les bénéfices). */
fun formatSignedPrice(amount: Double): String =
    if (amount > 0) "+" + formatPrice(amount) else formatPrice(amount)

private val shopAcronyms = setOf("GOAT", "SVD", "GLF", "BSTN", "SNKRS")
private val shopSpecialNames = mapOf("WETHENEW" to "WeTheNew", "MEET UP" to "Meet-up")

/** « VINTED » -> « Vinted », « WETHENEW » -> « WeTheNew », « NIKE / SNKRS » -> « Nike / SNKRS ». */
fun formatShopName(raw: String): String {
    shopSpecialNames[raw.uppercase()]?.let { return it }
    return raw.trim().split(" ").joinToString(" ") { word ->
        if (word.uppercase() in shopAcronyms) {
            word.uppercase()
        } else {
            word.lowercase().replaceFirstChar { it.titlecase() }
        }
    }
}
