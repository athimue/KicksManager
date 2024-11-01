package com.athimue.ui.composables.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composables.sells.SellUiModel

@Composable
fun SellItemCard(
    sell: SellUiModel
) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(sell.picture),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sell.name, fontWeight = FontWeight.ExtraBold
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Date : ${sell.sellDate}",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Size : ${sell.size}",
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Sell price : ${sell.sellPrice}  €",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Profit : ${sell.sellPrice - sell.buyPrice} €"
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Sell place : ${sell.sellPlace}",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SellItemCardPreview() {
    SellItemCard(
        sell = SellUiModel(
            id = 1,
            name = "",
            picture = "",
            size = "",
            buyPrice = 1.0,
            sellPrice = 1.0,
            sellDate = "",
            sellPlace = ""
        )
    )
}