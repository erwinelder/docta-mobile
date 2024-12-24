package cz.cvut.docta.lesson.presentation.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.component.containers.FullLengthFilterBar
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.presentation.utils.asStringRes
import org.jetbrains.compose.resources.stringResource

@Composable
fun LessonDifficultyFilterBar(
    activeDifficulty: LessonDifficulty?,
    onDifficultySelect: (LessonDifficulty?) -> Unit
) {
    FullLengthFilterBar(
        buttonStates = LessonDifficulty.entries.toList(),
        onIsButtonActive = { it == activeDifficulty },
        onGetButtonText = { stringResource(it.asStringRes()) },
        onButtonClick = { difficulty ->
            difficulty.takeIf { it != activeDifficulty }.let(onDifficultySelect)
        }
    )
}