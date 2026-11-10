package com.athimue.ui.composable.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
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

private const val TILES_PER_ROW = 3

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
                    value = uiState.stockPairs?.toString() ?: "–",
                    label = stringResource(R.string.stats_inventory),
                )
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = uiState.stockValue?.let { formatPrice(it) } ?: "–",
                    label = stringResource(R.string.stats_stock_value),
                    accent = MaterialTheme.colorScheme.tertiary,
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
                    accent = MaterialTheme.colorScheme.primary,
                )
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = formatPrice(uiState.expenses),
                    label = stringResource(R.string.stats_expenses),
                    accent = MaterialTheme.colorScheme.tertiary,
                )
            }
            TileRow {
                StatTile(
                    modifier = Modifier.weight(1f),
                    value = formatPrice(uiState.fees),
                    label = stringResource(R.string.stats_fees),
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
            if (uiState.platforms.isEmpty()) {
                Text(
                    text = stringResource(R.string.stats_platforms_empty),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            } else {
                Text(
                    text = stringResource(R.string.stats_platforms_hint),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 6.dp),
                )
                uiState.platforms.chunked(TILES_PER_ROW).forEach { rowPlatforms ->
                    TileRow {
                        rowPlatforms.forEach { platform ->
                            StatTile(
                                modifier = Modifier.weight(1f),
                                value = formatPrice(platform.sales),
                                label = formatShopName(platform.key),
                                accent = platformColor(platform.key),
                                onClick = { onClick(platform.key) },
                            )
                        }
                        // Cases vides pour garder des tuiles de même largeur sur la dernière ligne
                        repeat(TILES_PER_ROW - rowPlatforms.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
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

/** Couleur de marque des plateformes connues, couleur du thème sinon. */
@Composable
private fun platformColor(key: String): Color =
    when (key) {
        "VINTED" -> Color(0xFF09B1BA)
        "WETHENEW" -> Color(0xFFED9613)
        "ALIAS", "GOAT" -> Color(0xFF7857FF)
        "STOCKX" -> Color(0xFF006340)
        "DISCORD" -> Color(0xFF5865F2)
        "MEET UP" -> Color(0xFFE0115F)
        "BEFORE SHOP" -> Color(0xFFC6E000)
        else -> MaterialTheme.colorScheme.secondary
    }
