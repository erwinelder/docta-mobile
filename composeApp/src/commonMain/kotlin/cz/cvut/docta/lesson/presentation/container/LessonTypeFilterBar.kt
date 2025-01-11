package cz.cvut.docta.lesson.presentation.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.component.containers.FullLengthFilterBar
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.presentation.utils.asStringRes
import org.jetbrains.compose.resources.stringResource

@Composable
fun LessonTypeFilterBar(
    activeType: LessonFilterType?,
    onTypeSelect: (LessonFilterType?) -> Unit
) {
    FullLengthFilterBar(
        buttonStates = LessonFilterType.entries.toList(),
        onIsButtonActive = { it == activeType },
        onGetButtonText = { stringResource(it.asStringRes()) },
        onButtonClick = { type ->
            type.takeIf { it != activeType }.let(onTypeSelect)
        }
    )
}