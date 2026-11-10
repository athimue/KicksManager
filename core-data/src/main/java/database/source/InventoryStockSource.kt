package database.source

import kotlinx.coroutines.flow.Flow

/**
 * Accès en lecture au stock, partagé entre les features.
 *
 * L'inventaire vit dans la base de la feature « inventory » : cette interface permet aux autres
 * features (ex. statistiques) de lire le stock sans dépendre directement de feature-inventory.
 * L'implémentation est fournie par feature-inventory:data et injectée par Hilt.
 */
interface InventoryStockSource {
    fun observeStock(): Flow<InventoryStock>
}

data class InventoryStock(
    val pairs: Int,
    val value: Double,
)
