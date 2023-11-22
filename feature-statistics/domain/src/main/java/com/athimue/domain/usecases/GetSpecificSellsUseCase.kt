package com.athimue.domain.usecases

import com.athimue.domain.models.StatisticsSell
import kotlinx.coroutines.flow.Flow

interface GetSpecificSellsUseCase {
    fun invoke(filter: String): Flow<List<StatisticsSell>>
}