package com.athimue.ui.composable.trends

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.ui.composable.common.TrendItem
import com.athimue.ui.composable.detail.DetailModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendsComposable(
    viewModel: TrendsViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope,
    goToDetail: (DetailModel) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        if (uiState.popularSneakers.isNotEmpty()) {
            LazyRow {
                items(
                    items = uiState.popularSneakers,
                    key = { item -> item.sku },
                ) { popularSneaker ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                        Modifier.clickable {
                            goToDetail(
                                DetailModel(
                                    sku = popularSneaker.sku,
                                    picture = popularSneaker.picture,
                                    name = popularSneaker.name,
                                ),
                            )
                        },
                    ) {
                        TrendItem(
                            animatedVisibilityScope = animatedVisibilityScope,
                            picture = popularSneaker.picture,
                            name = popularSneaker.name,
                            sku = popularSneaker.sku,
                        )
                    }
                }
            }
        } else {
            Text("Empty list", textAlign = TextAlign.Center)
        }
        HorizontalDivider()
        Text(
            text = "JUST DROPPED",
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        if (uiState.justDroppedSneakers.isNotEmpty()) {
            LazyRow {
                items(
                    items = uiState.justDroppedSneakers,
                    key = { item -> item.sku },
                ) { justDroppedSneaker ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(justDroppedSneaker.picture),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp),
                        )
                        Text(
                            text =
                            if (justDroppedSneaker.name.length > 10) {
                                justDroppedSneaker.name.substring(
                                    0,
                                    8,
                                ) + ".."
                            } else {
                                justDroppedSneaker.name
                            },
                        )
                        Text(text = justDroppedSneaker.sku)
                    }
                }
            }
        } else {
            Text("Empty list", textAlign = TextAlign.Center)
        }
        HorizontalDivider()
        Text(
            text = "NEW ARRIVALS",
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        if (uiState.newArrivalsSneakers.isNotEmpty()) {
            LazyRow {
                items(
                    items = uiState.newArrivalsSneakers,
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
    }
}
