package com.athimue.ui.composables.sells

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.DeleteSellUseCase
import com.athimue.domain.usecases.GetSellsUseCase
import com.athimue.domain.usecases.RestoreSellUseCase
import com.athimue.domain.models.Sell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    getSellsUseCase: GetSellsUseCase,
    private val deleteSellUseCase: DeleteSellUseCase,
    private val restoreSellUseCase: RestoreSellUseCase,
) : ViewModel() {

    // Dernière vente supprimée, gardée pour le bouton « Annuler »
    private var lastDeletedSell: Sell? = null

    private val uiState =
        getSellsUseCase.invoke()
            .map { sells -> SellUiState(sells = sells.map { it.toSellUiModel() }) }
            .stateIn(
                scope = viewModelScope,
                initialValue = SellUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )
    val _uiState = uiState


    fun deleteSell(sellId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            lastDeletedSell = deleteSellUseCase.deleteSell(sellId)
        }
    }

    fun undoDelete() {
        val sell = lastDeletedSell ?: return
        lastDeletedSell = null
        viewModelScope.launch(Dispatchers.IO) {
            restoreSellUseCase.restoreSell(sell)
        }
    }
}
