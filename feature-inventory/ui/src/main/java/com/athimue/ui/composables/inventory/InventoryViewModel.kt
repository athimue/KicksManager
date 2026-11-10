package com.athimue.ui.composables.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.model.InventoryItem
import com.athimue.domain.model.InventorySort
import com.athimue.domain.usecase.AddOrUpdateInventoryUseCase
import com.athimue.domain.usecase.AddSellUseCase
import com.athimue.domain.usecase.DeleteInventoryUseCase
import com.athimue.domain.usecase.FilterInventoryUseCase
import com.athimue.domain.usecase.GetInventoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    getInventoryUseCase: GetInventoryUseCase,
    private val addOrUpdateInventoryUseCase: AddOrUpdateInventoryUseCase,
    private val addSellUseCase: AddSellUseCase,
    private val deleteInventoryUseCase: DeleteInventoryUseCase,
    private val filterInventoryUseCase: FilterInventoryUseCase,
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val sort = MutableStateFlow(InventorySort.RECENT)

    // Dernière paire supprimée, gardée pour le bouton « Annuler »
    private var lastDeletedItem: InventoryItem? = null

    private val uiState =
        combine(getInventoryUseCase(), query, sort) { items, currentQuery, currentSort ->
            InventoryUiState(
                inventory = filterInventoryUseCase(items, currentQuery, currentSort)
                    .map { inventoryItem -> inventoryItem.toInventoryUiModel() },
                totalPairs = items.sumOf { it.quantity },
                totalValue = items.sumOf { it.buyPrice * it.quantity },
                query = currentQuery,
                sort = currentSort,
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = InventoryUiState(),
            started = WhileSubscribed(5000),
        )

    val _uiState = uiState

    fun onQueryChange(newQuery: String) {
        query.value = newQuery
    }

    fun onSortChange(newSort: InventorySort) {
        sort.value = newSort
    }

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
            lastDeletedItem = deleteInventoryUseCase(inventoryItemId)
        }
    }

    fun undoDelete() {
        val item = lastDeletedItem ?: return
        lastDeletedItem = null
        viewModelScope.launch(Dispatchers.IO) {
            addOrUpdateInventoryUseCase(item)
        }
    }

    fun addSell(
        inventoryItemId: Long,
        sellPrice: String,
        sellDate: String,
        sellPlace: String,
        fees: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addSellUseCase(inventoryItemId, sellPrice, sellDate, sellPlace, fees)
        }
    }
}
