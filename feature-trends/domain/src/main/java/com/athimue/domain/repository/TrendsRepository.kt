package com.athimue.domain.repository

import com.athimue.domain.model.PopularSneaker

interface TrendsRepository {
    suspend fun getPopularSneakers(): Result<List<PopularSneaker>>
    suspend fun getJustDroppedSneakers(): Result<List<PopularSneaker>>
    suspend fun getNewArrivalsSneakers(): Result<List<PopularSneaker>>
}
