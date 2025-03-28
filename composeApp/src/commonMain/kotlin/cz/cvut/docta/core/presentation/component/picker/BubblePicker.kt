package cz.cvut.docta.core.presentation.component.picker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors

@Composable
fun BubblePicker(
    items: List<String>,
    selectedItem: String,
    onItemSelect: (String) -> Unit,
    onDimBackgroundChange: (Boolean) -> Unit = {}
) {
    val expandedState = remember { MutableTransitionState(false) }
    val selectedColor by animateColorAsState(
        targetValue = if (expandedState.targetState) DoctaColors.primary else DoctaColors.onSurface
    )

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        PickerButton(
            text = selectedItem,
            isExpanded = expandedState.targetState,
            selectedColor = selectedColor
        ) {
            onDimBackgroundChange(true)
            expandedState.targetState = true
        }
        Box {
            if (expandedState.targetState || expandedState.currentState || !expandedState.isIdle) {
                Popup(
                    onDismissRequest = {
                        onDimBackgroundChange(false)
                        expandedState.targetState = false
                    },
                    properties = PopupProperties(focusable = true)
                ) {
                    PopupContent(
                        items = items,
                        selectedItem = selectedItem,
                        onSelectItem = onItemSelect,
                        expandedState = expandedState,
                        onExpandedChange = {
                            onDimBackgroundChange(it)
                            expandedState.targetState = it
                        },
                        selectedColor = selectedColor
                    )
                }
            }
        }
    }
}

@Composable
private fun PopupContent(
    items: List<String>,
    selectedItem: String,
    onSelectItem: (String) -> Unit,
    expandedState: MutableTransitionState<Boolean>,
    onExpandedChange: (Boolean) -> Unit,
    selectedColor: Color
) {
    val lazyListState = rememberLazyListState()
    val itemAppearanceAnimSpeed = 300 / (items.size
        .takeIf { it != 0 } ?: 1)
        .let { it.takeUnless { it == 1 } ?: 2 }
    val itemAppearanceAnimationFloat: (Int) -> FiniteAnimationSpec<Float> = { orderNum ->
        tween(
            durationMillis = 300,
            delayMillis = itemAppearanceAnimSpeed * orderNum
        )
    }
    val itemAppearanceAnimationOffset: (Int) -> FiniteAnimationSpec<IntOffset> = { orderNum ->
        tween(
            durationMillis = 300,
            delayMillis = itemAppearanceAnimSpeed * orderNum
        )
    }

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        itemsIndexed(items = items) { index, item ->
            AnimatedVisibility(
                visibleState = expandedState,
                enter = fadeIn(itemAppearanceAnimationFloat(index)) +
                        scaleIn(itemAppearanceAnimationFloat(index)) +
                        slideInVertically(
                            animationSpec = itemAppearanceAnimationOffset(index),
                            initialOffsetY = { -it }
                        ),
                exit = fadeOut(tween(300)) +
                        scaleOut(tween(300)) +
                        slideOutVertically(
                            animationSpec = tween(300),
                            targetOffsetY = { -it }
                        )
            ) {
                Text(
                    text = item,
                    color = DoctaColors.onSurface.takeUnless { item == selectedItem } ?: selectedColor,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .bounceClickEffect {
                            onExpandedChange(false)
                            onSelectItem(item)
                        }
                        .shadow(
                            elevation = 0.dp,
                            shape = RoundedCornerShape(40)
                        )
                        .clip(RoundedCornerShape(40))
                        .background(
                            brush = Brush.linearGradient(
                                colors = DoctaColors.surfaceGradient,
                                start = Offset(75f, 210f),
                                end = Offset(95f, -10f)
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
