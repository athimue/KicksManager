package com.athimue.data.source

import com.athimue.data.database.dao.InventoryDao
import database.source.InventoryStock
import database.source.InventoryStockSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Expose le stock de l'inventaire aux autres features via l'interface partagée de core-data. */
class InventoryStockSourceImpl
    @Inject
    constructor(
        private val inventoryDao: InventoryDao,
    ) : InventoryStockSource {
        override fun observeStock(): Flow<InventoryStock> =
            inventoryDao.getAll().map { entities ->
                InventoryStock(
                    pairs = entities.sumOf { it.quantity },
                    value = entities.sumOf { it.buyPrice * it.quantity },
                )
            }
    }
