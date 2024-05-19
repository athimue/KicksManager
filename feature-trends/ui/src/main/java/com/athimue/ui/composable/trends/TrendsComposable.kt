package com.athimue.ui.composable.trends

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun TrendsComposable(viewModel: TrendsViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Trends")
        if (viewModel.uiState.popularSneaker.isNotEmpty()) {
            LazyRow {
                items(
                    items = viewModel.uiState.popularSneaker,
                    key = { item -> item.id },
                ) { popularSneaker ->
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(popularSneaker.picture),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp),
                        )
                        Text(text = popularSneaker.name)
                        Text(text = popularSneaker.id)
                    }
                }
            }
        } else {
            Text("VIDE")
        }
    }
}
