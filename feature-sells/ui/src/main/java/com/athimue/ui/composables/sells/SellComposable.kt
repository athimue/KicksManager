package com.athimue.ui.composables.sells

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.athimue.core.ui.EmptyState
import com.athimue.core.ui.KicksTopBar
import com.athimue.core.ui.SwipeActionBackground
import com.athimue.feature_sells.ui.R
import com.athimue.ui.composables.common.SellItemCard
import com.athimue.ui.composables.common.SellSummary

@Composable
fun SellsComposable(viewModel: SellViewModel = hiltViewModel()) {
    val uiState by viewModel._uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = { KicksTopBar(title = stringResource(R.string.sales_title)) },
    ) { innerPadding ->
        if (uiState.sells.isEmpty()) {
            EmptyState(
                modifier = Modifier.padding(innerPadding),
                emoji = "💸",
                title = stringResource(R.string.sales_empty_title),
                message = stringResource(R.string.sales_empty_message),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = innerPadding.calculateTopPadding() + 8.dp,
                    bottom = innerPadding.calculateBottomPadding() + 16.dp,
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item(key = "summary") {
                    SellSummary(uiState.sells)
                }
                items(items = uiState.sells, key = { item -> item.id }) { item ->
                    SellItem(
                        sell = item,
                        onEndToStartSwipe = {
                            viewModel.deleteSell(it)
                            Toast.makeText(
                                context,
                                context.getString(R.string.sales_toast_deleted),
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun LazyItemScope.SellItem(
    sell: SellUiModel,
    onEndToStartSwipe: (Long) -> Unit,
) {
    val currentItem by rememberUpdatedState(sell)
    val dismissState =
        rememberSwipeToDismissBoxState(confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.EndToStart -> {
                    onEndToStartSwipe(currentItem.id)
                    false
                }

                else -> false
            }
        }, positionalThreshold = { it * 0.35f })
    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            SwipeActionBackground(direction = dismissState.dismissDirection)
        },
    ) {
        SellItemCard(sell)
    }
}
