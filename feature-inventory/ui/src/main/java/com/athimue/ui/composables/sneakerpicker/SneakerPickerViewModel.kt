package com.athimue.ui.composables.sneakerpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecase.SearchSneakerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SneakerPickerViewModel @Inject constructor(
    private val searchSneakerUseCase: SearchSneakerUseCase,
) : ViewModel() {

    private val uiState: MutableStateFlow<SneakerPickerUiState> =
        MutableStateFlow(SneakerPickerUiState())
    val _uiState = uiState.asStateFlow()
    private var searchJob: Job? = null

    fun searchSneaker(query: String = "") {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            val response = searchSneakerUseCase(query)
            withContext(Dispatchers.Main) {
                uiState.update {
                    it.copy(
                        sneakers =
                        response.getOrElse { listOf() }
                            .map { it.toSneakerUiModel() },
                    )
                }
            }
        }
    }
}
