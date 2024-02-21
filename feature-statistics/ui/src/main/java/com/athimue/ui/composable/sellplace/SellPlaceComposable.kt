package com.athimue.ui.composable.sellplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composable.common.KeyFigureTitle

@Composable
fun SellPlaceStatisticsComposable(
    viewModel: SellPlaceViewModel = hiltViewModel(),
    sellPlace: String,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.loadSells(sellPlace)

    Column {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Go back",
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        KeyFigureTitle(title = "$sellPlace SUMMARY")
        if (uiState.sells.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
                items(items = uiState.sells) {
                    HorizontalDivider()
                    Row(
                        modifier =
                            Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(it.picture),
                            contentDescription = "",
                            modifier = Modifier.size(100.dp),
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = it.name,
                                fontWeight = FontWeight.ExtraBold,
                            )
                            Row(modifier = Modifier.padding(top = 10.dp)) {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Date : ${it.sellDate}",
                                )
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Size : ${it.size}",
                                )
                            }
                            Row(modifier = Modifier.padding(top = 10.dp)) {
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Profit : ${it.profit}  €",
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Text(
                text = "No sells",
                textAlign = TextAlign.Center,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
            )
        }
    }
}
