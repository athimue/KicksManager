package com.athimue.ui.sellplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.usecases.GetSpecificSellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SellPlaceViewModel @Inject constructor(
    private val getSpecificSellsUseCase: GetSpecificSellsUseCase
) : ViewModel() {
    var uiState = MutableStateFlow(SellPlaceUiState())

    fun loadSells(filter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                getSpecificSellsUseCase.invoke(filter).collect {
                    uiState.value =
                        uiState.value.copy(sells = it.map { sell -> sell.toSellPlaceUiModel() })
                }
            }
        }
    }
}