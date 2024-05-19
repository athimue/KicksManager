package com.athimue.ui.composable.trends

import com.athimue.domain.model.PopularSneaker

data class TrendsUiState(
  val popularSneaker: List<PopularSneaker> = listOf()
)
