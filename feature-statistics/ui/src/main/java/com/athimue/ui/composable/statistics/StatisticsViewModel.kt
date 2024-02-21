package com.athimue.ui.composable.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.athimue.domain.usecases.GetSellsStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel
    @Inject
    constructor(
        getSellsStatisticsUseCase: GetSellsStatisticsUseCase,
    ) : ViewModel() {
        val uiState =
            getSellsStatisticsUseCase.invoke()
                .map {
                    StatisticsUiState(
                        shoes = 15,
                        sells = it.size,
                        sales =
                            it.map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        expenses =
                            it.map { sell -> sell.buyPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        margin =
                            it.map { sell -> sell.sellPrice - sell.buyPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        wtnSells =
                            it.filter { sell -> sell.sellPlace == "WETHENEW" }
                                .map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        vintedSells =
                            it.filter { sell -> sell.sellPlace == "VINTED" }
                                .map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        goatSells =
                            it.filter { sell -> sell.sellPlace == "ALIAS" }
                                .map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        meetupSells =
                            it.filter { sell -> sell.sellPlace == "MEET_UP" }
                                .map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                        beforeShopSells =
                            it.filter { sell -> sell.sellPlace == "BEFORE_SHOP" }
                                .map { sell -> sell.sellPrice }
                                .fold(0.0) { acc, profitItem -> acc + profitItem },
                    )
                }
                .stateIn(
                    scope = viewModelScope,
                    initialValue = StatisticsUiState(),
                    started = SharingStarted.WhileSubscribed(5000),
                )
    }
