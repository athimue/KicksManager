package com.athimue.domain.usecases

import com.athimue.domain.models.Sell
import com.athimue.domain.repositories.SellRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteSellUseCaseImpl @Inject constructor(
    private val sellRepository: SellRepository
): DeleteSellUseCase {
    override suspend fun deleteSell(sellId: Long): Sell? {
        val deletedSell = sellRepository.getSells().first().find { it.id == sellId }
        sellRepository.deleteSell(sellId)
        return deletedSell
    }
}
