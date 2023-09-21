package com.athimue.ui.composables.sells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.athimue.domain.models.Sell

@Composable
fun SellsComposable(
    viewModel: SellViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = "SELLS",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 38.sp
        )
        SellSummary()
        LazyColumn {
            items(
                items = uiState.sells,
                key = { item -> item.id }
            ) { item ->
                SellItem(
                    sellItem = item,
                    onRemove = { viewModel.deleteSell(it) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun SellSummary() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SellSummaryItem(
            icon = Icons.Rounded.ThumbUp,
            iconColor = Color.Blue,
            title = "0",
            subject = "Sells"
        )
        SellSummaryItem(
            icon = Icons.Rounded.AddCircle,
            iconColor = Color.Green,
            title = "0.00",
            subject = "Beneficts"
        )
        SellSummaryItem(
            icon = Icons.Rounded.CheckCircle,
            iconColor = Color.Red,
            title = "0.00",
            subject = "CA"
        )
    }
}

@Composable
private fun SellSummaryItem(
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subject: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon,
            contentDescription = "",
            colorFilter = ColorFilter.tint(iconColor)
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = title)
            Text(text = subject)
        }
    }
}

@Composable
private fun SellItem(
    sellItem: Sell,
    onRemove: (Long) -> Unit
) {

}