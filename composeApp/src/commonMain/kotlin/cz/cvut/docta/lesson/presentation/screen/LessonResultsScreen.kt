package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.lesson.domain.model.LessonResults
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun LessonResultsScreen(
    results: LessonResults?,
    onContinueButtonClick: () -> Unit
) {
    AnimatedContent(
        targetState = results
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
                    text = stringResource(SharedRes.strings.continue_),
                    onClick = onContinueButtonClick
                )
            }
        }
    }
}