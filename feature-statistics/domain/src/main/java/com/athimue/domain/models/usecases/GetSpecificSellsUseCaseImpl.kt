package com.athimue.domain.models.usecases

import com.athimue.domain.models.StatisticsSell
import com.athimue.domain.models.repositories.StatisticsSellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSpecificSellsUseCaseImpl @Inject constructor(
    private val statisticsSellRepository: StatisticsSellRepository
) : GetSpecificSellsUseCase {
    override suspend fun invoke(filter: String): Flow<List<StatisticsSell>> =
        statisticsSellRepository.getSells().map {
            it.filter { sell ->
                sell.sellPlace.lowercase() == filter.lowercase()
            }
        }
}