package com.athimue.ui.composable.trends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun TrendsComposable(viewModel: TrendsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            text = "TRENDS",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 38.sp,
        )
        HorizontalDivider()
        Text(
            text = "MOST WANTED SNEAKERS",
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        if (viewModel.uiState.popularSneaker.isNotEmpty()) {
            LazyRow {
                items(
                    items = viewModel.uiState.popularSneaker,
                    key = { item -> item.sku },
                ) { popularSneaker ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(popularSneaker.picture),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp),
                        )
                        Text(
                            text =
                                if (popularSneaker.name.length > 10) {
                                    popularSneaker.name.substring(
                                        0,
                                        8,
                                    ) + ".."
                                } else {
                                    popularSneaker.name
                                },
                        )
                        Text(text = popularSneaker.sku)
                    }
                }
            }
        } else {
            Text("Empty list", textAlign = TextAlign.Center)
        }
        HorizontalDivider()
    }
}
