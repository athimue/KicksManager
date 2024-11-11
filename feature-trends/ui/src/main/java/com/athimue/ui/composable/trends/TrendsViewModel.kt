package com.athimue.ui.composable.trends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.repository.TrendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendsViewModel @Inject constructor(
    private val trendsRepository: TrendsRepository,
) : ViewModel() {

    val _uiState = MutableStateFlow(TrendsUiState())
    var uiState = _uiState.asStateFlow<TrendsUiState>()

    init {
        viewModelScope.run {
            launch {
                trendsRepository.getPopularSneakers().map { popularSneakers ->
                    _uiState.update {
                        it.copy(popularSneakers = popularSneakers)
                    }
                }
            }
            launch {
                trendsRepository.getJustDroppedSneakers().map { justDroppedSneakers ->
                    _uiState.update {
                        it.copy(justDroppedSneakers = justDroppedSneakers)
                    }
                }
            }
            launch {
                trendsRepository.getNewArrivalsSneakers().map { newArrivalsSneakers ->
                    _uiState.update {
                        it.copy(newArrivalsSneakers = newArrivalsSneakers)
                    }
                }
            }
        }
    }
}
