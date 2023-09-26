package com.athimue.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.models.usecases.GetSellsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getSellsUseCase: GetSellsUseCase
) : ViewModel() {
    val uiState = MutableStateFlow(StatisticsUiState())

    init {
        viewModelScope.launch {
            getSellsUseCase.invoke().collect {
                uiState.value = uiState.value.copy(
                    shoes = 15,
                    sells = it.size,
                    sales = it.map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    expenses = it.map { sell -> sell.buyPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    margin = it.map { sell -> sell.sellPrice - sell.buyPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    wtnSells = it.filter { sell -> sell.sellPlace == "WeTheNew" }
                        .map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    vintedSells = it.filter { sell -> sell.sellPlace == "Vinted" }
                        .map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    goatSells = it.filter { sell -> sell.sellPlace == "GOAT" }
                        .map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    meetupSells = it.filter { sell -> sell.sellPlace == "Face2Face" }
                        .map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                    beforeShopSells = it.filter { sell -> sell.sellPlace == "BeforeShop" }
                        .map { sell -> sell.sellPrice }
                        .fold(0.0) { acc, profitItem -> acc + profitItem },
                )
            }
        }
    }
}