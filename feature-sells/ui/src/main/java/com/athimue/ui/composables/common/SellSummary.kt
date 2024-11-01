package com.athimue.ui.composables.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.athimue.ui.composables.sells.SellUiModel

@Composable
fun SellSummary(sells: List<SellUiModel>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SellSummaryItem(
            icon = Icons.Rounded.ThumbUp,
            iconColor = Color.Blue,
            title = sells.size.toString(),
            subject = "Sells",
        )
        SellSummaryItem(
            icon = Icons.Rounded.AddCircle,
            iconColor = Color.Green,
            title = "${
                sells.map { sell -> sell.sellPrice - sell.buyPrice }
                    .fold(0.0) { acc, profitItem -> acc + profitItem }
            } €",
            subject = "Margin",
        )
        SellSummaryItem(
            icon = Icons.Rounded.CheckCircle,
            iconColor = Color.Red,
            title = "${
                sells.map { sell -> sell.sellPrice }
                    .fold(0.0) { acc, profitItem -> acc + profitItem }
            } €",
            subject = "Sales",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SellSummaryPreview() {
    SellSummary(
        sells = listOf()
    )
}

