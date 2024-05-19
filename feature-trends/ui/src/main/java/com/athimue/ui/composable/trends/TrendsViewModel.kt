package com.athimue.ui.composable.trends

import android.util.Log
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
            viewModelScope.launch {
                trendsRepository.getPopularItems().map {
                    uiState = uiState.copy(popularSneaker = it)
                }.onFailure { e ->
                    Log.d("COUCOU", "$e")
                }
            }
        }
    }
