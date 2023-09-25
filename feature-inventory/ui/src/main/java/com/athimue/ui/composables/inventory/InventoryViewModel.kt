package com.athimue.ui.composables.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getInventoryUseCase: GetInventoryUseCase,
    private val addInventoryUseCase: AddInventoryUseCase,
    private val addSellUseCase: AddSellUseCase,
    private val deleteInventoryUseCase: DeleteInventoryUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(InventoryUiState())

    init {
        viewModelScope.launch {
            getInventoryUseCase.invoke().collect {
                uiState.value = uiState.value.copy(inventory = it)
            }
        }
    }

    fun addInventoryItem(
        name: String,
        picture: String,
        size: String,
        buyPrice: Double,
        buyDate: String,
        buyPlace: String
    ) {
        viewModelScope.launch {
            addInventoryUseCase.invoke(
                InventoryItem(
                    id = -1,
                    name = name,
                    picture = picture,
                    size = size,
                    quantity = 1,
                    buyPrice = buyPrice,
                    buyDate = buyDate,
                    buyPlace = buyPlace,
                )
            )
        }
    }

    fun deleteInventoryItem(inventoryItemId: Long) {
        viewModelScope.launch {
            deleteInventoryUseCase.invoke(inventoryItemId)
        }
    }

    fun addSell(inventoryItemId: Long, sellPrice: String, sellDate: String, sellPlace: String) {
        viewModelScope.launch {
            addSellUseCase.invoke(inventoryItemId, sellPrice, sellDate, sellPlace)
        }
    }
}