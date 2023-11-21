package com.athimue.ui.composables.inventoryform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetInventoryItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InventoryFormModalViewModel @Inject constructor(
    private val getInventoryItemUseCase: GetInventoryItemUseCase,
) : ViewModel() {

    val uiState = MutableStateFlow(InventoryFormModalUiModel())

    fun loadInventoryItem(itemId: Long) {
        if (itemId != 0L) {
            viewModelScope.launch {
                getInventoryItemUseCase.invoke(itemId).first().let {
                    withContext(Dispatchers.Main) {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            id = it.id,
                            name = it.name,
                            size = it.size,
                            picture = it.picture,
                            buyPrice = it.buyPrice,
                            buyPlace = it.buyPlace,
                            buyDate = it.buyDate,
                        )
                    }
                }
            }
        } else {
            uiState.value = uiState.value.copy(
                isLoading = false,
                id = null,
                name = "",
                size = "",
                picture = "",
                buyPrice = 0.0,
                buyPlace = "",
                buyDate = "",
            )
        }
    }

    fun closePickers() {
        uiState.value = uiState.value.copy(
            showDatePicker = false,
            showSneakerPicker = false
        )
    }

    fun showSneakerPicker() {
        uiState.value = uiState.value.copy(
            showSneakerPicker = true
        )
    }

    fun setSize(size: String) {
        uiState.value = uiState.value.copy(
            size = size
        )
    }

    fun setBuyPrice(price: String) {
        uiState.value = uiState.value.copy(
            buyPrice = price.toDouble()
        )
    }

    fun showDatePicker() {
        uiState.value = uiState.value.copy(
            showSneakerPicker = true
        )
    }

    fun setBuyPlace(buyPlace: String) {
        uiState.value = uiState.value.copy(
            buyPlace = buyPlace
        )
    }

    fun setBuyDate(buyDate: String) {
        uiState.value = uiState.value.copy(
            buyDate = buyDate
        )
    }

    fun setNameAndPicture(name: String, picture: String) {
        uiState.value = uiState.value.copy(
            name = name,
            picture = picture
        )
    }
}