package com.athimue.ui.composables.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.athimue.core.ui.StatTile
import com.athimue.core.ui.formatPrice
import com.athimue.feature_inventory.ui.R
import com.athimue.ui.composables.inventory.InventoryUiModel

@Composable
fun SummaryHeader(
    inventory: List<InventoryUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatTile(
                modifier = Modifier.weight(1f),
                value = inventory.size.toString(),
                label = stringResource(R.string.inventory_stat_pairs),
            )
            StatTile(
                modifier = Modifier.weight(1f),
                value = formatPrice(inventory.sumOf { inventoryItem -> inventoryItem.buyPrice }),
                label = stringResource(R.string.inventory_stat_value),
                accent = MaterialTheme.colorScheme.tertiary,
            )
        }
        Text(
            text = stringResource(R.string.inventory_swipe_hint),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 12.dp, start = 4.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryHeaderPreview() {
    SummaryHeader(
        inventory = listOf(
            InventoryUiModel(
                id = 1L,
                name = "name",
                picture = "picture",
                size = "42",
                quantity = 1,
                buyPrice = 150.0
            )
        )
    )
}
