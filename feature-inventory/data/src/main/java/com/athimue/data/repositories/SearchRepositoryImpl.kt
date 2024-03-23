package com.athimue.data.repositories

import com.athimue.data.network.api.SellWeTheNewApi
import com.athimue.data.network.dto.toSneaker
import com.athimue.domain.model.Sneaker
import com.athimue.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl
    @Inject
    constructor(
        private val sellWeTheNewApi: SellWeTheNewApi,
    ) : SearchRepository {
        override suspend fun getSneakers(query: String): Result<List<Sneaker>> {
            val response = sellWeTheNewApi.getSneakers(query)
            return response.takeIf { it.isSuccessful }?.body()?.let {
                Result.success(it.results.map { sneakerDto -> sneakerDto.toSneaker() })
            } ?: Result.failure(Throwable("Erreur :/"))
        }
    }
