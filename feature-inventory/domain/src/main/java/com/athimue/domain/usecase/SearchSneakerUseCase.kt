package com.athimue.domain.usecase

import com.athimue.domain.repository.SearchRepository
import javax.inject.Inject

class SearchSneakerUseCase
    @Inject
    constructor(
        private val searchRepository: SearchRepository,
    ) {
        suspend operator fun invoke(query: String) = searchRepository.getSneakers(query)
    }
