package com.athimue.ui.composable.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.KicksColors
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.SectionTitle
import com.athimue.core.ui.StatTile
import com.athimue.core.ui.formatPrice
import com.athimue.core.ui.formatShopName
import com.athimue.core.ui.formatSignedPrice
import com.athimue.feature_statistics.ui.R

@Composable
fun StatisticsComposable(
    viewModel: StatisticsViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { KicksTopBar(title = stringResource(R.string.stats_title)) },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp),
        ) {
            SectionTitle(title = stringResource(R.string.stats_section_overview))
            TileRow {
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = "–",
                    label = stringResource(R.string.stats_inventory),
                )
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = uiState.sells.toString(),
                    label = stringResource(R.string.stats_sales),
                    accent = MaterialTheme.colorScheme.secondary,
                )
            }

            SectionTitle(title = stringResource(R.string.stats_section_money))
            TileRow {
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = formatPrice(uiState.sales),
                    label = stringResource(R.string.stats_revenue),
                    accent = MaterialTheme.colorScheme.tertiary,
                )
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = formatPrice(uiState.expenses),
                    label = stringResource(R.string.stats_expenses),
                    accent = MaterialTheme.colorScheme.error,
                )
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = formatSignedPrice(uiState.margin),
                    label = stringResource(R.string.stats_margin),
                    accent = KicksColors.success,
                    valueColor = if (uiState.margin < 0) MaterialTheme.colorScheme.error else KicksColors.success,
                )
            }

            SectionTitle(title = stringResource(R.string.stats_section_platforms))
            Text(
                text = stringResource(R.string.stats_platforms_hint),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 6.dp),
            )
            // La clé (« VINTED »…) doit rester identique à la valeur enregistrée avec la vente
            TileRow {
                PlatformTile("VINTED", uiState.vintedSells, Color(0xFF09B1BA), onClick)
                PlatformTile("WETHENEW", uiState.wtnSells, Color(0xFFED9613), onClick)
                PlatformTile("GOAT", uiState.goatSells, Color(0xFF7857FF), onClick)
            }
            TileRow {
                PlatformTile("DISCORD", 0.0, Color(0xFF5865F2))
                PlatformTile("BEFORE SHOP", uiState.beforeShopSells, Color(0xFFC6E000))
                PlatformTile("MEET UP", uiState.meetupSells, Color(0xFFE0115F))
            }
        }
    }
}

@Composable
private fun TileRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

@Composable
private fun RowScope.PlatformTile(
    key: String,
    amount: Double,
    brandColor: Color,
    onClick: ((String) -> Unit)? = null,
) {
    StatTile(
        modifier = Modifier.weight(1f),
        value = formatPrice(amount),
        label = formatShopName(key),
        accent = brandColor,
        onClick = onClick?.let { callback -> { callback(key) } },
    )
}
