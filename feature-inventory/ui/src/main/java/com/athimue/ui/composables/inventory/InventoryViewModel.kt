package com.athimue.ui.composables.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.usecases.*
import com.athimue.ui.composables.uimodels.toInventoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch(Dispatchers.IO) {
            getInventoryUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    uiState.value =
                        uiState.value.copy(inventory = it.map { it.toInventoryUiModel() })
                }
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            deleteInventoryUseCase.invoke(inventoryItemId)
        }
    }

    fun addSell(inventoryItemId: Long, sellPrice: String, sellDate: String, sellPlace: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addSellUseCase.invoke(inventoryItemId, sellPrice, sellDate, sellPlace)
        }
    }
}