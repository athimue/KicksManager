package com.athimue.ui.composable.trends

import com.athimue.domain.model.PopularSneaker

data class TrendsUiState(
  val popularSneakers: List<PopularSneaker> = listOf(),
  val justDroppedSneakers: List<PopularSneaker> = listOf(),
  val newArrivalsSneakers: List<PopularSneaker> = listOf()
)
