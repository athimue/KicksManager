package com.athimue.ui.composable.trends

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.SectionTitle
import com.athimue.domain.model.PopularSneaker
import com.athimue.feature_trends.ui.R
import com.athimue.ui.composable.common.TrendCard
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

    Scaffold(
        topBar = { KicksTopBar(title = stringResource(R.string.trends_title)) },
    ) { innerPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp),
        ) {
            TrendSection(
                title = stringResource(R.string.trends_most_wanted),
                sneakers = uiState.popularSneakers,
            ) { popularSneaker ->
                TrendItem(
                    animatedVisibilityScope = animatedVisibilityScope,
                    picture = popularSneaker.picture,
                    name = popularSneaker.name,
                    sku = popularSneaker.sku,
                    onClick = {
                        goToDetail(
                            DetailModel(
                                sku = popularSneaker.sku,
                                picture = popularSneaker.picture,
                                name = popularSneaker.name,
                            ),
                        )
                    },
                )
            }
            TrendSection(
                title = stringResource(R.string.trends_just_dropped),
                sneakers = uiState.justDroppedSneakers,
            ) { sneaker ->
                TrendCard(picture = sneaker.picture, name = sneaker.name, sku = sneaker.sku)
            }
            TrendSection(
                title = stringResource(R.string.trends_new_arrivals),
                sneakers = uiState.newArrivalsSneakers,
            ) { sneaker ->
                TrendCard(picture = sneaker.picture, name = sneaker.name, sku = sneaker.sku)
            }
        }
    }
}

@Composable
private fun TrendSection(
    title: String,
    sneakers: List<PopularSneaker>,
    itemContent: @Composable (PopularSneaker) -> Unit,
) {
    SectionTitle(title = title)
    if (sneakers.isEmpty()) {
        Text(
            text = stringResource(R.string.trends_empty),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    } else {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = sneakers, key = { item -> item.sku }) { sneaker ->
                itemContent(sneaker)
            }
        }
    }
}
