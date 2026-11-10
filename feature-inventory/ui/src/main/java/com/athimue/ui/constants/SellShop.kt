package com.athimue.ui.constants

/**
 * Plateformes de vente. La valeur [shopName] est enregistrée avec la vente et sert de clé
 * dans les statistiques : ne pas modifier les valeurs existantes.
 */
enum class SellShop(val shopName: String) {
    VINTED("VINTED"),
    WETHENEW("WETHENEW"),
    ALIAS("ALIAS"),
    STOCKX("STOCKX"),
    DISCORD("DISCORD"),
    MEET_UP("MEET UP"),
    OTHER("OTHER"),
}
