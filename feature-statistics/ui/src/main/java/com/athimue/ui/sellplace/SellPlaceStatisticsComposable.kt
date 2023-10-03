package com.athimue.ui.sellplace

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SellPlaceStatisticsComposable(
    sellPlace: String, onBack: () -> Unit
) {
    Column() {
        Button(onClick = onBack) {
            Text("Back")
        }
        Text(text = "SELL PLACE $sellPlace")
    }
}