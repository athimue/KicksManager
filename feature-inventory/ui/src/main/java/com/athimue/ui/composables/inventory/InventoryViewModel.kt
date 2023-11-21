package com.athimue.ui.composables.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.usecases.AddOrUpdateInventoryUseCase
import com.athimue.domain.usecases.AddSellUseCase
import com.athimue.domain.usecases.DeleteInventoryUseCase
import com.athimue.domain.usecases.GetInventoryUseCase
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
    private val deleteInventoryUseCase: DeleteInventoryUseCase
) : ViewModel() {

    val uiState = getInventoryUseCase.invoke()
        .map { InventoryUiState(inventory = it.map { inventoryItem -> inventoryItem.toInventoryUiModel() }) }
        .stateIn(
            scope = viewModelScope,
            initialValue = InventoryUiState(),
            started = WhileSubscribed(5000)
        )

    fun addOrUpdateInventoryItem(
        id: Long?,
        name: String,
        picture: String,
        size: String,
        buyPrice: Double,
        buyDate: String,
        buyPlace: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addOrUpdateInventoryUseCase.invoke(
                InventoryItem(
                    id = id ?: 0,
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