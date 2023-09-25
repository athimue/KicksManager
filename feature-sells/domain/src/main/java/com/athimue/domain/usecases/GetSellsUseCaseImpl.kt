package com.athimue.domain.usecases

import com.athimue.domain.models.Sell
import com.athimue.domain.repositories.SellRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSellsUseCaseImpl @Inject constructor(
    private val sellRepository: SellRepository
) : GetSellsUseCase {

    override suspend fun invoke(): Flow<List<Sell>> =
        sellRepository.getSells()

}