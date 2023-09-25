package com.athimue.domain.usecases

import com.athimue.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchSneakerUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchSneakerUseCase {
    override suspend fun invoke(query: String) = searchRepository.getSneakers(query)
}