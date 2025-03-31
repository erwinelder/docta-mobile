package cz.cvut.docta.core.presentation.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cz.cvut.docta.core.presentation.animation.scaleFadeInAnimation
import cz.cvut.docta.core.presentation.animation.scaleFadeOutAnimation
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun IconButtonWithPopupContent(
    iconRes: DrawableResource? = null,
    animationTransformOrigin: TransformOrigin = TransformOrigin(0.5f, 0.5f),
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
    popupContent: @Composable (() -> Unit) -> Unit
) {
    val isExpandedState = remember { MutableTransitionState(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        SmallSecondaryIconButton(
            iconRes = iconRes,
            onClick = { isExpandedState.targetState = true }
        )

        Column {
            if (isExpandedState.targetState || isExpandedState.currentState) {
                Popup(
                    alignment = Alignment.TopEnd,
                    offset = IntOffset(0, 48),
                    onDismissRequest = {
                        isExpandedState.targetState = false
                    },
                    properties = PopupProperties(
                        focusable = true
                    )
                ) {
                    AnimatedVisibility(
                        visibleState = isExpandedState,
                        enter = scaleFadeInAnimation(animationTransformOrigin),
                        exit = scaleFadeOutAnimation(animationTransformOrigin)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            popupContent {
                                isExpandedState.targetState = false
                            }
                        }
                    }
                }
            }
        }
    }
}
