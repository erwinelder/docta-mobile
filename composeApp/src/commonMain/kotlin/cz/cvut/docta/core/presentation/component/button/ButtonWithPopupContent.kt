package cz.cvut.docta.core.presentation.component.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import cz.cvut.docta.core.presentation.animation.scaleFadeInAnimation
import cz.cvut.docta.core.presentation.animation.scaleFadeOutAnimation
import cz.cvut.docta.core.presentation.theme.DoctaColors
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ButtonWithPopupContent(
    buttonText: String,
    iconRes: DrawableResource? = null,
    animationTransformOrigin: TransformOrigin = TransformOrigin(0.5f, 0.5f),
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
    popupContent: @Composable (() -> Unit) -> Unit
) {
    val isExpandedState = remember { MutableTransitionState(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        SmallSecondaryButton(
            text = buttonText,
            iconRes = iconRes,
            onClick = { isExpandedState.targetState = true }
        )

        Column {
            if (isExpandedState.targetState || isExpandedState.currentState) {
                Popup(
                    alignment = Alignment.TopCenter,
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
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                                .clip(RoundedCornerShape(26.dp))
                                .background(DoctaColors.surface)
                                .padding(contentPadding)
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
