package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.statistics.ProgressBar
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState.Completed.CourseUnitCompletedIcon

@Composable
fun CourseWithProgressComponent(
    course: CourseWithProgress,
    filledWidths: FilledWidthByScreenType? = FilledWidthByScreenType(),
    onClick: (CourseWithProgress) -> Unit
) {
    GlassSurface(
        cornerSize = 20.dp,
        borderSize = 0.dp,
        filledWidths = filledWidths,
        modifier = Modifier.bounceClickEffect(.98f) {
            onClick(course)
        }
    ) {
        when (course) {
            is CourseWithProgress.NotStarted -> CourseNotStartedComponentContent(course)
            is CourseWithProgress.InProgress -> CourseInProgressComponentContent(course)
            is CourseWithProgress.Completed -> CourseCompletedComponentContent(course)
        }
    }
}

@Composable
private fun CourseNotStartedComponentContent(course: CourseWithProgress.NotStarted) {
    val completionState = CourseUnitCompletionMarkState.NotStarted.initialize()

    CourseUnitDefaultComponentContent(unitName = course.name, completionState = completionState)
}

@Composable
private fun CourseCompletedComponentContent(course: CourseWithProgress.Completed) {
    val completionState = CourseUnitCompletionMarkState.Completed.initialize(
        icon = CourseUnitCompletedIcon.Checked
    )

    CourseUnitDefaultComponentContent(unitName = course.name, completionState = completionState)
}

@Composable
private fun CourseInProgressComponentContent(course: CourseWithProgress.InProgress) {
    val completionState = CourseUnitCompletionMarkState.InProgress.initialize(
        nextStep = course.completed + 1
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(78.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 12.dp)
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, end = 4.dp)
            ) {
                CourseUnitNameComponent(name = course.name)
            }
            ProgressBar(
                percentage = course.completed / course.sectionCount.toFloat(),
                gradientColor = DoctaColors.primaryGradient,
                shadowColor = DoctaColors.primary,
                height = 8.dp
            )
        }
        CourseUnitCompletionMarkComponent(completionState = completionState)
    }
}

