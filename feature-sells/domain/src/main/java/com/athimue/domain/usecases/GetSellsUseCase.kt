package com.athimue.domain.usecases

import com.athimue.domain.models.Sell
import kotlinx.coroutines.flow.Flow

interface GetSellsUseCase {
    fun invoke() : Flow<List<Sell>>
}