package com.athimue.ui.composable.sellplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.EmptyState
import com.athimue.core.ui.InfoChip
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.ProfitText
import com.athimue.core.ui.SneakerCard
import com.athimue.core.ui.formatShopName
import com.athimue.feature_statistics.ui.R

@Composable
fun SellPlaceStatisticsComposable(
    viewModel: SellPlaceViewModel = hiltViewModel(),
    sellPlace: String,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = sellPlace) {
        viewModel.loadSells(sellPlace)
    }

    Scaffold(
        topBar = {
            KicksTopBar(
                title = formatShopName(sellPlace),
                subtitle = stringResource(R.string.stats_sellplace_subtitle),
                onBack = onBack,
            )
        },
    ) { innerPadding ->
        if (uiState.sells.isEmpty()) {
            EmptyState(
                modifier = Modifier.padding(innerPadding),
                emoji = "📭",
                title = stringResource(R.string.stats_sellplace_empty_title),
                message = stringResource(R.string.stats_sellplace_empty_message),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 8.dp,
                    bottom = innerPadding.calculateBottomPadding() + 16.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = uiState.sells) { sell ->
                    SneakerCard(
                        name = sell.name,
                        picture = sell.picture,
                        details = {
                            InfoChip(text = stringResource(R.string.stats_size, sell.size))
                            if (sell.sellDate.isNotBlank()) InfoChip(text = sell.sellDate)
                        },
                        trailing = {
                            ProfitText(
                                amount = sell.profit,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        },
                    )
                }
            }
        }
    }
}
