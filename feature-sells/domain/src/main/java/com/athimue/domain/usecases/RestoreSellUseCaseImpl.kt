package com.athimue.domain.usecases

import com.athimue.domain.models.Sell
import com.athimue.domain.repositories.SellRepository
import javax.inject.Inject

class RestoreSellUseCaseImpl @Inject constructor(
    private val sellRepository: SellRepository
) : RestoreSellUseCase {
    override suspend fun restoreSell(sell: Sell) {
        sellRepository.restoreSell(sell)
    }
}
