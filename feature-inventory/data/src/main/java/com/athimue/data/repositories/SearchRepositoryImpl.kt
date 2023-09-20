package com.athimue.data.repositories

import android.util.Log
import com.athimue.data.network.api.SellWeTheNewApi
import com.athimue.data.network.dto.toSneaker
import com.athimue.domain.models.Sneaker
import com.athimue.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val sellWeTheNewApi: SellWeTheNewApi
) : SearchRepository {

    override suspend fun getSneakers(query: String): Result<List<Sneaker>> {
        val response = sellWeTheNewApi.getSneakers()
        Log.d("COUCOU", "ici")
        Log.d("COUCOU", response.body().toString())
        return response.takeIf { it.isSuccessful }?.body()?.let {
            Result.success(it.results.map { sneakerDto -> sneakerDto.toSneaker() })
        } ?: Result.failure(Throwable("Erreur :/"))
    }
}