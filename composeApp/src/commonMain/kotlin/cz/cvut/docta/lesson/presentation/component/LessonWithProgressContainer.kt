package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.CurrAppTheme
import cz.cvut.docta.course.presentation.component.CourseUnitCompletionMarkComponent
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState.Completed.CourseUnitCompletedIcon
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.presentation.utils.getLessonIconRes
import org.jetbrains.compose.resources.painterResource

@Composable
fun LessonWithProgressContainer(
    lesson: LessonWithProgress,
    height: Dp,
    filledWidths: FilledWidthByScreenType? = FilledWidthByScreenType(),
    onClick: (LessonWithProgress) -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val completionState = if (lesson.completed) {
        CourseUnitCompletionMarkState.Completed.initialize(icon = CourseUnitCompletedIcon.Repeat)
    } else {
        CourseUnitCompletionMarkState.NotStarted.initialize()
    }

    GlassSurface(
        cornerSize = 20.dp,
        borderSize = 0.dp,
        filledWidths = filledWidths,
        modifier = Modifier.bounceClickEffect {
            onClick(lesson)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(height)
                .padding(start = 16.dp)
        ) {
            Image(
                painter = painterResource(lesson.getLessonIconRes().get(CurrAppTheme)),
                contentDescription = "lesson icon",
                modifier = Modifier.size(40.dp)
            )
            content()
            CourseUnitCompletionMarkComponent(completionState = completionState)
        }
    }
}