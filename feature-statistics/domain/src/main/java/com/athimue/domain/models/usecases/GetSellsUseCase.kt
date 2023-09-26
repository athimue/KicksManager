package com.athimue.domain.models.usecases

import com.athimue.domain.models.StatisticsSell
import kotlinx.coroutines.flow.Flow

interface GetSellsUseCase {
    suspend fun invoke() : Flow<List<StatisticsSell>>
}