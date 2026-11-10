package com.athimue.ui.composable.common

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.athimue.core.ui.SneakerImage

private val CardWidth = 148.dp

/** Carte de sneaker pour les carrousels de tendances. */
@Composable
fun TrendCard(
    picture: String,
    name: String,
    sku: String,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    nameModifier: Modifier = Modifier,
    skuModifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val content: @Composable () -> Unit = {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            SneakerImage(
                url = picture,
                size = CardWidth - 20.dp,
                modifier = imageModifier,
            )
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = nameModifier,
            )
            Text(
                text = sku,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = skuModifier,
            )
        }
    }
    if (onClick != null) {
        ElevatedCard(onClick = onClick, modifier = modifier.width(CardWidth)) { content() }
    } else {
        ElevatedCard(modifier = modifier.width(CardWidth)) { content() }
    }
}

/** Même carte, avec l'animation d'élément partagé vers l'écran de détail. */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendItem(
    animatedVisibilityScope: AnimatedVisibilityScope,
    picture: String,
    name: String,
    sku: String,
    onClick: () -> Unit,
) {
    TrendCard(
        picture = picture,
        name = name,
        sku = sku,
        onClick = onClick,
        imageModifier = Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "image/$picture"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
        nameModifier = Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "text/$name"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
        skuModifier = Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "text/$sku"),
            animatedVisibilityScope = animatedVisibilityScope,
        ),
    )
}
