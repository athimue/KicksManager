package com.athimue.domain.models.usecases

import com.athimue.domain.models.StatisticsSell
import kotlinx.coroutines.flow.Flow

interface GetSpecificSellsUseCase {
    suspend fun invoke(filter: String): Flow<List<StatisticsSell>>
}