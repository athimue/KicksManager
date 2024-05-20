package com.athimue.ui.composable.trends

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.repository.TrendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendsViewModel
    @Inject
    constructor(
        private val trendsRepository: TrendsRepository,
    ) : ViewModel() {
        var uiState by mutableStateOf(TrendsUiState())

        init {
            viewModelScope.run {
                launch {
                    trendsRepository.getPopularSneakers().map {
                        uiState = uiState.copy(popularSneakers = it)
                    }
                }
                launch {
                    trendsRepository.getJustDroppedSneakers().map {
                        uiState = uiState.copy(justDroppedSneakers = it)
                    }
                }
            }
        }
    }
