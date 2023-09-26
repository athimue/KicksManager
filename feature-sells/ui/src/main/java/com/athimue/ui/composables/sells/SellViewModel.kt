package com.athimue.ui.composables.sells

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.DeleteSellUseCase
import com.athimue.domain.usecases.GetSellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    private val getSellsUseCase: GetSellsUseCase,
    private val deleteSellUseCase: DeleteSellUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(SellUiState())

    init {
        viewModelScope.launch {
            getSellsUseCase.invoke().collect {
                uiState.value = uiState.value.copy(sells = it.map { it.toSellUiModel() })
            }
        }
    }

    fun deleteSell(sellId: Long) {
        viewModelScope.launch {
            deleteSellUseCase.deleteSell(sellId)
        }
    }
}