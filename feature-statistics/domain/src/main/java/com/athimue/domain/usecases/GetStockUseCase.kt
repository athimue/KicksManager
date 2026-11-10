package com.athimue.domain.usecases

import com.athimue.domain.models.StockSummary
import kotlinx.coroutines.flow.Flow

interface GetStockUseCase {
    fun invoke(): Flow<StockSummary>
}
