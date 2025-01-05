package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.lesson.domain.model.LessonResults

@Composable
fun LessonResultsScreen(
    results: LessonResults?,
    onContinueButtonClick: () -> Unit
) {
    AnimatedContent(
        targetState = results,
        label = "lesson results"
    ) { targetResults ->
        if (targetResults != null) {
            ScreenContainer {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = targetResults.successRate.toString()
                    )
                }
                PrimaryButton(
                    // TODO-STRING-RESOURCES
                    text = "Continue",
                    onClick = onContinueButtonClick
                )
            }
        }
    }
}