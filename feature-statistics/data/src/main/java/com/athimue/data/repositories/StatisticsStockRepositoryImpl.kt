package com.athimue.data.repositories

import com.athimue.domain.models.StockSummary
import com.athimue.domain.repositories.StatisticsStockRepository
import database.source.InventoryStockSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Lit le stock via l'interface partagée de core-data (implémentée par feature-inventory). */
class StatisticsStockRepositoryImpl @Inject constructor(
    private val inventoryStockSource: InventoryStockSource
) : StatisticsStockRepository {
    override fun getStock(): Flow<StockSummary> =
        inventoryStockSource.observeStock().map { stock ->
            StockSummary(pairs = stock.pairs, value = stock.value)
        }
}
