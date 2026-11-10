package com.athimue.domain.repositories

import com.athimue.domain.models.StockSummary
import kotlinx.coroutines.flow.Flow

interface StatisticsStockRepository {
    fun getStock(): Flow<StockSummary>
}
