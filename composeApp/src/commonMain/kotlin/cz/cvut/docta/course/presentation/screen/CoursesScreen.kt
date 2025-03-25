package cz.cvut.docta.course.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.other.GreetingsMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.presentation.component.CourseWithProgressComponent
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.add_icon
import docta.composeapp.generated.resources.edit_icon

@Composable
fun CoursesScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    username: String,
    onAddNewCourse: () -> Unit,
    onEditCourses: () -> Unit,
    courses: List<CourseWithProgress>,
    onCourseClick: (CourseWithProgress) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(48.dp),
        screenPadding = screenPadding
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            GreetingsMessage(username = username)
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
        AnimatedContent(
            targetState = courses
        ) { targetCourses ->
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(
                    FilledWidthByScreenType().getByType(CurrWindowType)
                )
            ) {
                items(items = targetCourses) { course ->
                    CourseWithProgressComponent(
                        course = course,
                        filledWidths = null,
                        onClick = onCourseClick
                    )
                }
            }
        }
    }
}