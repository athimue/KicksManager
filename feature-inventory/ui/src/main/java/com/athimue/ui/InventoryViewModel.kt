package com.athimue.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetInventoryUseCase
import com.athimue.domain.usecases.SearchSneakerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val searchSneakerUseCase: SearchSneakerUseCase,
    private val getInventoryUseCase: GetInventoryUseCase
) : ViewModel() {
    var uiState: MutableStateFlow<InventoryUiState> =
        MutableStateFlow(InventoryUiState())

    init {
        viewModelScope.launch {
            getInventoryUseCase.invoke().collect {
                uiState.value = uiState.value.copy(inventory = it)
            }
        }
    }

    fun searchSneaker(query: String = "dunk") {
        viewModelScope.launch {
            val response = searchSneakerUseCase.invoke(query)
            uiState.value =
                uiState.value.copy(sneakerResult = response.getOrNull())
        }
    }
}