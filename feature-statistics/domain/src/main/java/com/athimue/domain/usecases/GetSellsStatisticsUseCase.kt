package com.athimue.domain.usecases

import com.athimue.domain.models.StatisticsSell
import kotlinx.coroutines.flow.Flow

interface GetSellsStatisticsUseCase {
    fun invoke(): Flow<List<StatisticsSell>>
}