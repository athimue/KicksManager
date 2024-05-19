package com.athimue.domain.usecase

import com.athimue.domain.model.PopularSneaker
import com.athimue.domain.repository.TrendsRepository
import javax.inject.Inject

class GetPopularItemUseCase
    @Inject
    constructor(private val trendsRepository: TrendsRepository) {
        suspend operator fun invoke(): Result<List<PopularSneaker>> = trendsRepository.getPopularItems()
    }
