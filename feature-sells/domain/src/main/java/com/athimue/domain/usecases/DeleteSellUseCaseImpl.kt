package com.athimue.domain.usecases

import com.athimue.domain.repositories.SellRepository
import javax.inject.Inject

class DeleteSellUseCaseImpl @Inject constructor(
    private val sellRepository: SellRepository
): DeleteSellUseCase {

    override suspend fun deleteSell(sellId: Long) {
        sellRepository.deleteSell(sellId)
    }

}