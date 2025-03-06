package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.course.domain.model.Course

@Composable
fun CourseNavButtonComponent(
    course: Course,
    onClick: (Course) -> Unit
) {
    GlassSurfaceNavigationButton(
        text = course.name,
        padding = PaddingValues(
            start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
        ),
        cornerSize = 18.dp,
        onClick = {
            onClick(course)
        }
    )
}