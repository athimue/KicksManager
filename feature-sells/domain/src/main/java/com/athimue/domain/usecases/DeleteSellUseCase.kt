package com.athimue.domain.usecases

interface DeleteSellUseCase {
    suspend fun deleteSell(sellId: Long)
}