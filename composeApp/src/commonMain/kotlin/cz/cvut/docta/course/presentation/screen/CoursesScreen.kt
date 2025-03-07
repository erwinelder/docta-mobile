package cz.cvut.docta.course.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.other.GreetingsMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.presentation.component.CourseNavButtonComponent
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.add_icon
import docta.composeapp.generated.resources.edit_icon

@Composable
fun CoursesScreen(
    onAddNewCourse: () -> Unit,
    onEditCourses: () -> Unit,
    courses: List<Course>,
    onCourseClick: (Course) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(48.dp),
        padding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GreetingsMessage()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SmallSecondaryButton(
                    text = stringResource(SharedRes.strings.add_new_course),
                    iconRes = Res.drawable.add_icon,
                    onClick = onAddNewCourse
                )
                SmallSecondaryButton(
                    text = stringResource(SharedRes.strings.edit_courses),
                    iconRes = Res.drawable.edit_icon,
                    onClick = onEditCourses
                )
            }
        }
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = courses) { course ->
                CourseNavButtonComponent(
                    course = course,
                    onClick = onCourseClick
                )
            }
        }
    }
}
