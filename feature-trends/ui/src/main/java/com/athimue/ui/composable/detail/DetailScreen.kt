package com.athimue.ui.composable.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    detail: DetailModel,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = rememberAsyncImagePainter(detail.picture),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(120.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${detail.picture}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
            )
            Text(
                text = detail.name,
                modifier =
                    Modifier.sharedElement(
                        state = rememberSharedContentState(key = "text/${detail.name}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
            )
            Text(
                text = detail.sku,
                modifier =
                    Modifier.sharedElement(
                        state = rememberSharedContentState(key = "text/${detail.sku}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
            )
        }
    }
}
