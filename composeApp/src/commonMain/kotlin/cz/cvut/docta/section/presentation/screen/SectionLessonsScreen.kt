package cz.cvut.docta.section.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.presentation.component.OneStepQuestionsLessonComponent
import cz.cvut.docta.lesson.presentation.component.StepByStepLessonComponent
import cz.cvut.docta.lesson.presentation.component.TestLessonComponent
import cz.cvut.docta.lesson.presentation.container.LessonDifficultyFilterBar
import cz.cvut.docta.lesson.presentation.container.LessonTypeFilterBar
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun SectionLessonsScreen(
    sectionName: String,
    sectionIconRes: DrawableResource? = null,
    onNavigateBack: () -> Unit,
    activeDifficulty: LessonDifficulty?,
    onDifficultyChange: (LessonDifficulty) -> Unit,
    activeType: LessonFilterType?,
    onTypeSelect: (LessonFilterType) -> Unit,
    lessonList: List<Lesson>
) {
    val lazyListState = rememberLazyListState()

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GlassSurfaceTopBackNavButton(
                text = sectionName,
                iconRes = sectionIconRes,
                onClick = onNavigateBack
            )
            LessonTypeFilterBar(
                activeType = activeType,
                onTypeSelect = onTypeSelect
            )
            LessonDifficultyFilterBar(
                activeDifficulty = activeDifficulty,
                onDifficultySelect = onDifficultyChange
            )
        }
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items = lessonList) { lesson ->
                when (lesson) {
                    is Lesson.OneStepQuestionsLesson -> {
                        OneStepQuestionsLessonComponent(state = lesson) {}
                    }
                    is Lesson.StepByStepLesson -> {
                        StepByStepLessonComponent(state = lesson) {}
                    }
                    is Lesson.TestLesson -> {
                        TestLessonComponent(state = lesson) {}
                    }
                }
            }
        }
    }
}