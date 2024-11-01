package com.athimue.ui.composable.common

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    picture: String,
    name: String,
    sku: String,
) {
    Image(
        painter = rememberAsyncImagePainter(picture),
        contentDescription = null,
        modifier =
        Modifier
            .size(120.dp)
            .sharedElement(
                state = rememberSharedContentState(key = "image/$picture"),
                animatedVisibilityScope = animatedVisibilityScope,
            ),
    )
    Text(
        text =
        if (name.length > 10) {
            name.substring(
                0,
                8,
            ) + ".."
        } else {
            name
        },
        modifier =
        Modifier.sharedElement(
            state = rememberSharedContentState(key = "text/$name"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
    )
    Text(
        text = sku,
        modifier =
        Modifier.sharedElement(
            state = rememberSharedContentState(key = "text/$sku"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
    )
}
