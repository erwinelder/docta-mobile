package cz.cvut.docta.core.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ColumnScope.KeyboardTypingAnimatedVisibilityContainer(
    isVisible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedContent(
        targetState = isVisible,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(700)
            ) togetherWith fadeOut(
                animationSpec = tween(700)
            )
        },
        contentAlignment = Alignment.Center
    ) { visible ->
        if (visible) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                content()
            }
        }
    }
}