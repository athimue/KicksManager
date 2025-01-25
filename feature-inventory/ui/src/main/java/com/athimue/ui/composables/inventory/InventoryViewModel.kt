package com.athimue.ui.composables.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.InventoryItem
import com.athimue.domain.usecase.AddOrUpdateInventoryUseCase
import com.athimue.domain.usecase.AddSellUseCase
import com.athimue.domain.usecase.DeleteInventoryUseCase
import com.athimue.domain.usecase.GetInventoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    getInventoryUseCase: GetInventoryUseCase,
    private val addOrUpdateInventoryUseCase: AddOrUpdateInventoryUseCase,
    private val addSellUseCase: AddSellUseCase,
    private val deleteInventoryUseCase: DeleteInventoryUseCase,
) : ViewModel() {

    private val uiState =
        getInventoryUseCase()
            .map { InventoryUiState(inventory = it.map { inventoryItem -> inventoryItem.toInventoryUiModel() }) }
            .stateIn(
                scope = viewModelScope,
                initialValue = InventoryUiState(),
                started = WhileSubscribed(5000),
            )

    val _uiState = uiState

    fun addOrUpdateInventoryItem(
        id: Long,
        name: String,
        picture: String,
        size: String,
        buyPrice: Double,
        buyDate: String,
        buyPlace: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addOrUpdateInventoryUseCase(
                InventoryItem(
                    id = id,
                    name = name,
                    picture = picture,
                    size = size,
                    quantity = 1,
                    buyPrice = buyPrice,
                    buyDate = buyDate,
                    buyPlace = buyPlace,
                ),
            )
        }
    }

    fun deleteInventoryItem(inventoryItemId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteInventoryUseCase(inventoryItemId)
        }
    }

    fun addSell(
        inventoryItemId: Long,
        sellPrice: String,
        sellDate: String,
        sellPlace: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addSellUseCase(inventoryItemId, sellPrice, sellDate, sellPlace)
        }
    }
}
