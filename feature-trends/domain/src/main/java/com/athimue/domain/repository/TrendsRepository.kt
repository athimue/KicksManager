package com.athimue.domain.repository

import com.athimue.domain.model.PopularSneaker

interface TrendsRepository {
    suspend fun getPopularItems(): Result<List<PopularSneaker>>
}
