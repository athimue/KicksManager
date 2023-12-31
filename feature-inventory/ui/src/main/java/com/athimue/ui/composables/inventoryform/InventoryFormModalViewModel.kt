package com.athimue.ui.composables.inventoryform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetInventoryItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryFormModalViewModel @Inject constructor(
    private val getInventoryItemUseCase: GetInventoryItemUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryFormModalUiModel())
    val uiState: StateFlow<InventoryFormModalUiModel> = _uiState

    fun loadInventoryItem(itemId: Long) {
        viewModelScope.launch {
            if (itemId != 0L) {
                getInventoryItemUseCase.invoke(itemId).first().let { item ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        id = item.id,
                        name = item.name,
                        size = item.size,
                        picture = item.picture,
                        buyPrice = item.buyPrice,
                        buyPlace = item.buyPlace,
                        buyDate = item.buyDate,
                    )
                }
            } else {
                _uiState.value = _uiState.value.copy(
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
    }

    fun closeDatePicker() = updateState { copy(showDatePicker = false) }
    fun closeSneakerPicker() = updateState { copy(showSneakerPicker = false) }
    fun showDatePicker() = updateState { copy(showDatePicker = true) }
    fun showSneakerPicker() = updateState { copy(showSneakerPicker = true) }
    fun setSize(size: String) = updateState { copy(size = size) }
    fun setBuyPrice(price: String) = updateState { copy(buyPrice = price.toDouble()) }
    fun setBuyPlace(buyPlace: String) = updateState { copy(buyPlace = buyPlace) }
    fun setBuyDate(buyDate: String) = updateState { copy(buyDate = buyDate) }
    fun setNameAndPicture(name: String, picture: String) =
        updateState { copy(name = name, picture = picture) }

    private fun updateState(block: InventoryFormModalUiModel.() -> InventoryFormModalUiModel) {
        _uiState.value = _uiState.value.block()
    }
}