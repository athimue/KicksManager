package com.athimue.ui.composables.sells

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.athimue.domain.models.Sell
import com.athimue.ui.composables.common.SellSummary

@Composable
fun SellsComposable(
    viewModel: SellViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = "SELLS",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 38.sp
        )
        SellSummary(uiState.sells)
        Divider()
        LazyColumn {
            items(items = uiState.sells, key = { item -> item.id }) { item ->
                SellItem(
                    sell = item,
                    onEndToStartSwipe = {
                        viewModel.deleteSell(it)
                        Toast.makeText(
                            context,
                            "Sell deleted !",
                            Toast.LENGTH_SHORT
                        ).show()}
                )
                Divider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.SellItem(
    sell: SellUiModel,
    onEndToStartSwipe: (Long) -> Unit
) {
    val currentItem by rememberUpdatedState(sell)
    val dismissState = rememberDismissState(confirmValueChange = {
        when (it) {
            DismissValue.DismissedToStart -> {
                onEndToStartSwipe(currentItem.id)
                false
            }
            else -> false
        }
    }, positionalThreshold = { 0.35f })
    SwipeToDismiss(state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp)
            .animateItemPlacement(),
        directions = setOf(DismissDirection.EndToStart),
        background = {
            DismissBackground(dismissState)
        },
        dismissContent = {
            SellItemCard(sell)
        })
}


@Composable
private fun SellItemCard(
    sell: SellUiModel
) {
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(sell.picture),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sell.name, fontWeight = FontWeight.ExtraBold
            )
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Date : ${sell.sellDate}",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Size : ${sell.size}",
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Sell price : ${sell.sellPrice}  €",
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Profit : ${sell.sellPrice - sell.buyPrice} €"
                )
            }
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = "Sell place : ${sell.sellPlace}",
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return
    val color = when (direction) {
        DismissDirection.EndToStart -> Color(0xFFFF1744)
        else -> Color.Transparent
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}