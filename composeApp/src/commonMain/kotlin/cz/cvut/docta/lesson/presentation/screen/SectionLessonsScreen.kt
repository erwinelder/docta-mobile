package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.errorHandling.presentation.component.container.RequestStateComponent
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.presentation.component.LessonWithProgressComponent
import cz.cvut.docta.lesson.presentation.container.LessonTypeFilterBar
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun SectionLessonsScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    sectionName: String,
    sectionIconRes: DrawableResource? = null,
    onNavigateBack: () -> Unit,
    activeType: LessonFilterType?,
    onTypeSelect: (LessonFilterType?) -> Unit,
    lessons: List<LessonWithProgress>,
    onLessonClick: (LessonWithProgress) -> Unit,
    requestState: RequestState?
) {
    ScreenContainerWithBackNavButton(
        screenPadding = screenPadding,
        padding = PaddingValues(top = 8.dp),
        onNavigateBack = onNavigateBack,
        backButtonText = sectionName,
        backButtonIconRes = sectionIconRes
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            LessonTypeFilterBar(
                activeType = activeType,
                onTypeSelect = onTypeSelect
            )
        }
        LessonsBlockWithLoader(
            requestState = requestState,
            lessons = lessons,
            onLessonClick = onLessonClick
        )
    }
}

@Composable
private fun ColumnScope.LessonsBlockWithLoader(
    requestState: RequestState?,
    lessons: List<LessonWithProgress>,
    onLessonClick: (LessonWithProgress) -> Unit
) {
    val lazyListState = rememberLazyListState()

    AnimatedContent(
        targetState = requestState to lessons
    ) { (state, targetLessons) ->
        if (state != null) {
            RequestStateComponent(state = state)
        } else {
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items = targetLessons) { lesson ->
                    LessonWithProgressComponent(lesson = lesson, onClick = onLessonClick)
                }
            }
        }
    }
}