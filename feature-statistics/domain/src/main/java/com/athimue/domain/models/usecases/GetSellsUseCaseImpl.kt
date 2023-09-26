package com.athimue.domain.models.usecases

import com.athimue.domain.models.StatisticsSell
import com.athimue.domain.models.repositories.StatisticsSellRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSellsUseCaseImpl @Inject constructor(
    private val statisticsSellRepository: StatisticsSellRepository
) : GetSellsUseCase {

    override suspend fun invoke(): Flow<List<StatisticsSell>> =
        statisticsSellRepository.getSells()

}