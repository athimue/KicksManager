package com.athimue.ui.composables.inventory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.InventoryItem
import com.athimue.domain.usecases.AddInventoryUseCase
import com.athimue.domain.usecases.DeleteInventoryUseCase
import com.athimue.domain.usecases.GetInventoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getInventoryUseCase: GetInventoryUseCase,
    private val addInventoryUseCase: AddInventoryUseCase,
    private val deleteInventoryUseCase: DeleteInventoryUseCase
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
        Log.d("COUCOU", inventoryItemId.toString())
        viewModelScope.launch {
            deleteInventoryUseCase.invoke(inventoryItemId)
        }
    }
}