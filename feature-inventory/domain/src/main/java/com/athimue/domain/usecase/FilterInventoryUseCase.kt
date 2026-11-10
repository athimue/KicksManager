package com.athimue.domain.usecase

import com.athimue.domain.model.InventoryItem
import com.athimue.domain.model.InventorySort
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

/**
 * Recherche (nom, taille ou boutique) et tri de l'inventaire.
 * Les dates d'achat sont enregistrées au format « dd/MM/yyyy ».
 */
class FilterInventoryUseCase
    @Inject
    constructor() {
        operator fun invoke(
            items: List<InventoryItem>,
            query: String,
            sort: InventorySort,
        ): List<InventoryItem> {
            val search = query.trim()
            val filtered =
                if (search.isEmpty()) {
                    items
                } else {
                    items.filter { item ->
                        item.name.contains(search, ignoreCase = true) ||
                            item.size.contains(search, ignoreCase = true) ||
                            item.buyPlace.contains(search, ignoreCase = true)
                    }
                }
            return when (sort) {
                InventorySort.RECENT ->
                    filtered.sortedWith(
                        compareByDescending<InventoryItem> { it.buyDate.toEpochMillis() }.thenByDescending { it.id },
                    )

                InventorySort.PRICE_ASC -> filtered.sortedBy { it.buyPrice }
                InventorySort.PRICE_DESC -> filtered.sortedByDescending { it.buyPrice }
                InventorySort.NAME -> filtered.sortedBy { it.name.lowercase() }
                InventorySort.SIZE ->
                    filtered.sortedWith(
                        compareBy<InventoryItem> { it.size.replace(',', '.').toDoubleOrNull() ?: Double.MAX_VALUE }
                            .thenBy { it.size },
                    )
            }
        }

        private fun String.toEpochMillis(): Long =
            runCatching { SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).parse(this)?.time }.getOrNull() ?: 0L
    }
