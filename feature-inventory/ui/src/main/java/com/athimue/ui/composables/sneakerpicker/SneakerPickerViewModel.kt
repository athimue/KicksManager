package com.athimue.ui.composables.sneakerpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.SearchSneakerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SneakerPickerViewModel @Inject constructor(
    private val searchSneakerUseCase: SearchSneakerUseCase
) : ViewModel() {

    var uiState: MutableStateFlow<SneakerPickerUiState> = MutableStateFlow(SneakerPickerUiState())

    private var searchJob: Job? = null

    fun searchSneaker(query: String = "") {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            val response = searchSneakerUseCase.invoke(query)
            withContext(Dispatchers.Main) {
                uiState.value = uiState.value.copy(sneakers = response.getOrElse { listOf() }
                    .map { it.toSneakerUiModel() })
            }
        }
    }
}