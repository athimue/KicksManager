package com.athimue.ui.composables.sneakerpicker

import com.athimue.domain.models.Sneaker

data class SneakerPickerUiState(
    var sneakers: List<Sneaker> = listOf()
)