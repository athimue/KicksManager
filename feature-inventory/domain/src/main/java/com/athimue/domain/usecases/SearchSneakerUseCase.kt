package com.athimue.domain.usecases

import com.athimue.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchSneakerUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun invoke(query: String) = searchRepository.getSneakers(query)
}