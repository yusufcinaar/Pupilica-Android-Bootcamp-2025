package com.example.foodorderapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun <T> OptimizedLazyList(
    items: List<T>,
    key: ((item: T) -> Any)? = null,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(0.dp),
    itemContent: @Composable (item: T) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val firstVisibleItemIndex = remember { mutableStateOf(0) }
    val loadedItems = remember { mutableStateOf<Set<Int>>(emptySet()) }

    // Monitor scroll state
    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                firstVisibleItemIndex.value = index
            }
    }

    // Load items in viewport and buffer
    LaunchedEffect(firstVisibleItemIndex.value, items.size) {
        val start = (firstVisibleItemIndex.value - 10).coerceAtLeast(0)
        val end = (firstVisibleItemIndex.value + 20).coerceAtMost(items.size)
        loadedItems.value = (start..end).toSet()
    }

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        items(
            items = items,
            key = key
        ) { item ->
            val index = items.indexOf(item)
            val shouldLoad = loadedItems.value.contains(index)
            if (shouldLoad) {
                itemContent(item)
            } else {
                // Placeholder while loading
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun rememberScrollContext(
    listState: LazyListState = rememberLazyListState()
): ScrollContext {
    val scope = rememberCoroutineScope()
    return remember(listState, scope) {
        ScrollContext(
            listState = listState,
            coroutineScope = scope
        )
    }
}

class ScrollContext(
    val listState: LazyListState,
    val coroutineScope: kotlinx.coroutines.CoroutineScope
) {
    val isScrollInProgress: Boolean
        get() = listState.isScrollInProgress

    fun scrollToItem(index: Int) {
        coroutineScope.launch {
            listState.scrollToItem(index)
        }
    }

    fun scrollToTop() {
        coroutineScope.launch {
            listState.scrollToItem(0)
        }
    }
}
