package com.athimue.ui.composables.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.athimue.ui.composables.inventory.InventoryUiModel

@Composable
fun SummaryHeader(inventory: List<InventoryUiModel>) {
    HorizontalDivider()
    Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Text(
            text = "${inventory.size} items",
            modifier = Modifier.padding(start = 10.dp),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "${
                inventory.map { inventoryItem -> inventoryItem.buyPrice }
                    .fold(0.0) { acc, buyPriceItem -> acc + buyPriceItem }
            } €",
            modifier = Modifier.padding(end = 10.dp),
        )
    }
    HorizontalDivider()
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
