package cz.cvut.docta.section.presentation.component

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
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.component.statistics.ProgressBar
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.course.presentation.component.CourseUnitCompletionMarkComponent
import cz.cvut.docta.course.presentation.component.CourseUnitDefaultComponentContent
import cz.cvut.docta.course.presentation.component.CourseUnitNameComponent
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState.Completed.CourseUnitCompletedIcon
import cz.cvut.docta.section.domain.model.SectionWithProgress

@Composable
fun SectionWithProgressComponent(
    section: SectionWithProgress,
    filledWidths: FilledWidthByScreenType? = FilledWidthByScreenType(),
    onClick: (SectionWithProgress) -> Unit
) {
    GlassSurface(
        cornerSize = 20.dp,
        borderSize = 0.dp,
        filledWidths = filledWidths,
        modifier = Modifier.bounceClickEffect {
            onClick(section)
        }
    ) {
        when (section) {
            is SectionWithProgress.NotStarted -> SectionNotStartedComponentContent(section)
            is SectionWithProgress.InProgress -> CourseInProgressComponentContent(section)
            is SectionWithProgress.Completed -> SectionCompletedComponentContent(section)
        }
    }
}

@Composable
private fun SectionNotStartedComponentContent(section: SectionWithProgress.NotStarted) {
    val completionState = CourseUnitCompletionMarkState.NotStarted.initialize()

    CourseUnitDefaultComponentContent(unitName = section.name, completionState = completionState)
}

@Composable
private fun SectionCompletedComponentContent(section: SectionWithProgress.Completed) {
    val completionState = CourseUnitCompletionMarkState.Completed.initialize(
        icon = CourseUnitCompletedIcon.Checked
    )

    CourseUnitDefaultComponentContent(unitName = section.name, completionState = completionState)
}

@Composable
private fun CourseInProgressComponentContent(section: SectionWithProgress.InProgress) {
    val completionState = CourseUnitCompletionMarkState.InProgress.initialize(
        nextStep = section.completed + 1
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
                CourseUnitNameComponent(name = section.name)
            }
            ProgressBar(
                percentage = section.completed / section.lessonCount.toFloat(),
                gradientColor = DoctaColors.primaryGradient,
                shadowColor = DoctaColors.primary,
                height = 8.dp
            )
        }
        CourseUnitCompletionMarkComponent(completionState = completionState)
    }
}

