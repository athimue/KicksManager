package com.athimue.ui.composable.sellplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetSpecificSellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SellPlaceViewModel @Inject constructor(
    private val getSpecificSellsUseCase: GetSpecificSellsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SellPlaceUiState())
    val uiState = _uiState.asStateFlow()

    fun loadSells(filter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSpecificSellsUseCase.invoke(filter).collect { sells ->
                withContext(Dispatchers.Main) {
                    _uiState.update {
                        it.copy(
                            sells = sells.map { sell -> sell.toSellPlaceUiModel() }
                        )
                    }
                }
            }
        }
    }
}
