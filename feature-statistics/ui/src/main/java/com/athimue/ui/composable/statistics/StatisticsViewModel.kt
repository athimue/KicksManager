package com.athimue.ui.composable.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetSellsStatisticsUseCase
import com.athimue.domain.usecases.GetStockUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel
    @Inject
    constructor(
        getSellsStatisticsUseCase: GetSellsStatisticsUseCase,
        getStockUseCase: GetStockUseCase,
    ) : ViewModel() {
        val uiState =
            combine(getSellsStatisticsUseCase.invoke(), getStockUseCase.invoke()) { sells, stock ->
                StatisticsUiState(
                    stockPairs = stock.pairs,
                    stockValue = stock.value,
                    sells = sells.size,
                    sales = sells.sumOf { sell -> sell.sellPrice },
                    expenses = sells.sumOf { sell -> sell.buyPrice },
                    fees = sells.sumOf { sell -> sell.fees },
                    margin = sells.sumOf { sell -> sell.netProfit },
                    // Plateformes calculées à partir des ventes réelles (plus de liste figée)
                    platforms =
                        sells
                            .groupBy { sell -> sell.sellPlace.trim().uppercase() }
                            .filterKeys { it.isNotEmpty() }
                            .map { (key, platformSells) ->
                                PlatformStatistics(
                                    key = key,
                                    sales = platformSells.sumOf { sell -> sell.sellPrice },
                                    count = platformSells.size,
                                )
                            }
                            .sortedByDescending { it.sales },
                )
            }.stateIn(
                scope = viewModelScope,
                initialValue = StatisticsUiState(),
                started = SharingStarted.WhileSubscribed(5000),
            )
    }
