package com.athimue.ui.composable.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.SneakerImage
import com.athimue.feature_trends.ui.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detail: DetailModel,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            KicksTopBar(
                title = stringResource(R.string.trends_title),
                onBack = onBackClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SneakerImage(
                url = detail.picture,
                size = 280.dp,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState(key = "image/${detail.picture}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            )
            Text(
                text = detail.name,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState(key = "text/${detail.name}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            )
            Text(
                text = stringResource(R.string.trends_sku, detail.sku),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.sharedElement(
                    sharedContentState = rememberSharedContentState(key = "text/${detail.sku}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            )
        }
    }
}
