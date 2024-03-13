package com.athimue.domain.repository

import com.athimue.domain.model.Sneaker

interface SearchRepository {
    suspend fun getSneakers(query: String): Result<List<Sneaker>>
}