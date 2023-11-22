package com.athimue.domain.usecases

import com.athimue.domain.models.StatisticsSell
import com.athimue.domain.repositories.StatisticsSellRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSellsStatisticsUseCaseImpl @Inject constructor(
    private val statisticsSellRepository: StatisticsSellRepository
) : GetSellsStatisticsUseCase {

    override fun invoke(): Flow<List<StatisticsSell>> =
        statisticsSellRepository.getSells()

}