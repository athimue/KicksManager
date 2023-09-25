package com.athimue.domain.usecases

import com.athimue.domain.models.Sneaker

interface SearchSneakerUseCase {
    suspend fun invoke(query: String): Result<List<Sneaker>>
}