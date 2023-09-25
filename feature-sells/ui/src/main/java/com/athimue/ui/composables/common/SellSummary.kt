package com.athimue.ui.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.athimue.domain.models.Sell

@Composable
fun SellSummary(
    sells: List<Sell>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SellSummaryItem(
            icon = Icons.Rounded.ThumbUp,
            iconColor = Color.Blue,
            title = sells.size.toString(),
            subject = "Sells"
        )
        SellSummaryItem(icon = Icons.Rounded.AddCircle, iconColor = Color.Green, title = "${
            sells.map { sell -> sell.sellPrice - sell.buyPrice }
                .fold(0.0) { acc, profitItem -> acc + profitItem }
        } €", subject = "Beneficts")
        SellSummaryItem(icon = Icons.Rounded.CheckCircle, iconColor = Color.Red, title = "${
            sells.map { sell -> sell.sellPrice }.fold(0.0) { acc, profitItem -> acc + profitItem }
        } €", subject = "CA")
    }
}

@Composable
private fun SellSummaryItem(
    icon: ImageVector, iconColor: Color, title: String, subject: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon, contentDescription = "", colorFilter = ColorFilter.tint(iconColor)
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = title)
            Text(text = subject)
        }
    }
}