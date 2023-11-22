package com.athimue.domain.repositories

import com.athimue.domain.models.StatisticsSell
import kotlinx.coroutines.flow.Flow

interface StatisticsSellRepository {

    fun getSells(): Flow<List<StatisticsSell>>
}