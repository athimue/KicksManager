package com.athimue.domain.repositories

import com.athimue.domain.models.Sneaker

interface SearchRepository {
    suspend fun getSneakers(query: String): Result<List<Sneaker>>
}