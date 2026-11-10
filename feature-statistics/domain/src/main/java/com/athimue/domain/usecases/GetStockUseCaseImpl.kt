package com.athimue.domain.usecases

import com.athimue.domain.models.StockSummary
import com.athimue.domain.repositories.StatisticsStockRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStockUseCaseImpl @Inject constructor(
    private val statisticsStockRepository: StatisticsStockRepository
) : GetStockUseCase {
    override fun invoke(): Flow<StockSummary> =
        statisticsStockRepository.getStock()
}
