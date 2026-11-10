package com.athimue.domain.repositories

import com.athimue.domain.models.Sell
import kotlinx.coroutines.flow.Flow

interface SellRepository {

    suspend fun deleteSell(sellId: Long)
    suspend fun restoreSell(sell: Sell)

    fun getSells(): Flow<List<Sell>>
}