package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.errorHandling.presentation.component.container.RequestStateComponent
import cz.cvut.docta.errorHandling.presentation.model.RequestState

@Composable
fun LessonStarterScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    onNavigateBack: () -> Unit
) {
    ScreenContainer(
        screenPadding = screenPadding
    ) {
        AnimatedVisibility(
            visible = requestState != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if (requestState != null) {
                RequestStateComponent(
                    state = requestState,
                    fill = true,
                    onErrorClose = onNavigateBack
                )
            }
        }
    }
}