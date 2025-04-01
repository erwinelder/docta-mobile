package cz.cvut.docta.core.presentation.component.picker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cz.cvut.docta.core.presentation.animation.scaleFadeInAnimation
import cz.cvut.docta.core.presentation.animation.scaleFadeOutAnimation
import cz.cvut.docta.core.presentation.component.divider.SmallDivider
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun <T> PopupPicker(
    selectedItem: T,
    itemList: List<T>,
    itemToStringMapper: @Composable (T) -> String,
    onItemSelect: (T) -> Unit,
    outerPadding: PaddingValues = PaddingValues(0.dp),
    fontSize: TextUnit = 19.sp,
    buttonGradientColor: Pair<Color, Color> = DoctaColors.glassSurfaceGradientPair,
    enabled: Boolean = true
) {
    val isExpandedState = remember { MutableTransitionState(false) }
    val selectedColor by animateColorAsState(
        targetValue = if (isExpandedState.targetState)
            DoctaColors.primaryText else DoctaColors.onSurface
    )
    val buttonLighterColor by animateColorAsState(targetValue = buttonGradientColor.first)
    val buttonDarkerColor by animateColorAsState(targetValue = buttonGradientColor.second)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(outerPadding)
    ) {
        PickerButton(
            text = itemToStringMapper(selectedItem),
            isExpanded = isExpandedState.targetState,
            selectedColor = selectedColor,
            fontSize = fontSize,
            gradientColor = listOf(buttonLighterColor, buttonDarkerColor),
            enabled = enabled
        ) {
            isExpandedState.targetState = true
        }
        Column {
            if (isExpandedState.targetState || isExpandedState.currentState) {
                Popup(
                    alignment = Alignment.TopCenter,
                    onDismissRequest = { isExpandedState.targetState = false },
                    properties = PopupProperties(focusable = true)
                ) {
                    AnimatedVisibility(
                        visibleState = isExpandedState,
                        enter = scaleFadeInAnimation(TransformOrigin(.5f, 0f)),
                        exit = scaleFadeOutAnimation(TransformOrigin(.5f, 0f))
                    ) {
                        PopupContent(
                            selectedItem = selectedItem,
                            itemList = itemList,
                            itemToStringMapper = itemToStringMapper,
                            onItemSelect = {
                                onItemSelect(it)
                                isExpandedState.targetState = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> PopupContent(
    selectedItem: T,
    itemToStringMapper: @Composable (T) -> String,
    itemList: List<T>,
    onItemSelect: (T) -> Unit,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp, 12.dp),
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .shadow(10.dp, RoundedCornerShape(20.dp))
            .background(DoctaColors.surface)
    ) {
        itemsIndexed(items = itemList) { index, item ->
            val itemText = itemToStringMapper(item)

            if (index != 0) {
                SmallDivider(modifier = Modifier.padding(bottom = 8.dp))
            }
            Text(
                text = itemText,
                color = if (itemText == itemToStringMapper(selectedItem))
                    DoctaColors.primaryText else DoctaColors.onSurface,
                fontSize = 19.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Manrope,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.bounceClickEffect {
                    onItemSelect(item)
                }
            )
        }
    }
}