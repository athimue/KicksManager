package com.athimue.ui.composables.sneakerpicker

import com.athimue.ui.composables.uimodels.SneakerUiModel

data class SneakerPickerUiState(
    var sneakers: List<SneakerUiModel> = listOf()
)