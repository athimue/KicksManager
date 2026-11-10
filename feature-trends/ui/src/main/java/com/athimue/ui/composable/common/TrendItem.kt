package com.athimue.ui.composable.common

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

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
                sharedContentState = rememberSharedContentState(key = "image/$picture"),
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
            sharedContentState = rememberSharedContentState(key = "text/$name"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
    )
    Text(
        text = sku,
        modifier =
        Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "text/$sku"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
    )
}
