package cz.cvut.docta.course.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.container.MessageContainerSmallSecondaryButton
import cz.cvut.docta.core.presentation.component.other.GreetingsMessage
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.presentation.component.CourseWithProgressComponent
import cz.cvut.docta.errorHandling.presentation.component.container.RequestStateComponent
import cz.cvut.docta.errorHandling.presentation.model.RequestState
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
    onCourseClick: (CourseWithProgress) -> Unit,
    requestState: RequestState?
) {
    val isCoursesEmpty by remember(courses) {
        derivedStateOf { courses.isEmpty() }
    }

    ScreenContainer(
        screenPadding = screenPadding,
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            GreetingsMessage(username = username)
            ButtonsBlock(
                isCoursesEmpty = isCoursesEmpty,
                onAddNewCourse = onAddNewCourse,
                onEditCourses = onEditCourses
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CoursesBlock(
                requestState = requestState,
                courses = courses,
                onCourseClick = onCourseClick,
                onAddNewCourse = onAddNewCourse
            )
        }
    }
}

@Composable
private fun ColumnScope.ButtonsBlock(
    isCoursesEmpty: Boolean,
    onAddNewCourse: () -> Unit,
    onEditCourses: () -> Unit
) {
    AnimatedContent(
        targetState = !isCoursesEmpty,
    ) { visible ->
        if (visible) {
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
    }
}

@Composable
private fun BoxScope.CoursesBlock(
    requestState: RequestState?,
    courses: List<CourseWithProgress>,
    onCourseClick: (CourseWithProgress) -> Unit,
    onAddNewCourse: () -> Unit
) {
    val lazyListState = rememberLazyListState()

    AnimatedContent(
        targetState = requestState to courses
    ) { (state, targetCourses) ->
        if (state != null) {
            RequestStateComponent(state = state)
        } else if (courses.isNotEmpty()) {
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                    .fillMaxHeight()
            ) {
                items(items = targetCourses) { course ->
                    CourseWithProgressComponent(
                        course = course,
                        filledWidths = null,
                        onClick = onCourseClick
                    )
                }
            }
        } else {
            MessageContainerSmallSecondaryButton(
                text = stringResource(SharedRes.strings.no_courses_message),
                buttonText = stringResource(SharedRes.strings.add_new_course),
                buttonIconRes = Res.drawable.add_icon,
                onClick = onAddNewCourse
            )
        }
    }
}