package com.athimue.data.repositories

import com.athimue.data.network.api.GoatApi
import com.athimue.data.network.dto.toPopularSneaker
import com.athimue.domain.model.PopularSneaker
import com.athimue.domain.repository.TrendsRepository
import javax.inject.Inject

class TrendsRepositoryImpl
    @Inject
    constructor(
        private val goatApi: GoatApi,
    ) : TrendsRepository {

    override suspend fun getPopularSneakers(): Result<List<PopularSneaker>> {
        val response = goatApi.getPopularSneakers()
        return response.takeIf { it.isSuccessful }?.body()?.let {
            Result.success(it.response.results.map { popularSneakerDto -> popularSneakerDto.toPopularSneaker() })
        } ?: Result.failure(Exception(response.message()))
    }

    override suspend fun getJustDroppedSneakers(): Result<List<PopularSneaker>> {
        val response = goatApi.getJustDroppedSneakers()
        return response.takeIf { it.isSuccessful }?.body()?.let {
            Result.success(it.response.results.map { popularSneakerDto -> popularSneakerDto.toPopularSneaker() })
        } ?: Result.failure(Exception(response.message()))
    }
}
