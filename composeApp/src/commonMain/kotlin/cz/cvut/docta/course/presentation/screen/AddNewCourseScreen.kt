package cz.cvut.docta.course.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.buttons.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.field.LargeTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseSearchState
import cz.cvut.docta.course.presentation.component.CourseComponent
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.add
import docta.composeapp.generated.resources.add_icon
import docta.composeapp.generated.resources.add_new_course
import docta.composeapp.generated.resources.cancel
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.course_search_placeholder
import docta.composeapp.generated.resources.reset_icon
import docta.composeapp.generated.resources.search
import docta.composeapp.generated.resources.search_icon
import docta.composeapp.generated.resources.searching_for_course
import docta.composeapp.generated.resources.try_again
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddNewCourseScreen(
    onNavigateBack: () -> Unit,
    searchedCourseState: CourseSearchState,
    query: String,
    onQueryChange: (String) -> Unit,
    searchIsAllowed: Boolean,
    onSearch: () -> Unit,
    onSearchCancel: () -> Unit,
    onTryAgain: () -> Unit,
    onAddCourse: (Course) -> Unit
) {
    ScreenContainerWithBackNavButton(
        onBackButtonClick = onNavigateBack,
        backButtonText = stringResource(Res.string.add_new_course)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedContent(
                targetState = searchedCourseState
            ) { state ->
                when (state) {
                    is CourseSearchState.Prompt -> CourseSearchPromptComponent(
                        query = query,
                        onQueryChange = onQueryChange,
                        searchIsAllowed = searchIsAllowed,
                        onSearch = onSearch
                    )
                    is CourseSearchState.Loading -> CourseSearchLoadingComponent(
                        state = state,
                        onCancel = onSearchCancel
                    )
                    is CourseSearchState.SearchedCourse -> CourseSearchSearchedCourseComponent(
                        state = state,
                        onTryAgain = onTryAgain,
                        onAddCourse = onAddCourse
                    )
                }
            }
        }
    }
}

@Composable
private fun CourseSearchPromptComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    searchIsAllowed: Boolean,
    onSearch: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LargeTextField(
            text = query,
            placeholderText = stringResource(Res.string.course_search_placeholder),
            onValueChange = onQueryChange
        )
        SmallPrimaryButton(
            text = stringResource(Res.string.search),
            iconRes = Res.drawable.search_icon,
            enabled = searchIsAllowed,
            onClick = onSearch
        )
    }
}

@Composable
private fun CourseSearchLoadingComponent(
    state: CourseSearchState.Loading,
    onCancel: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.searching_for_course) + " \"${state.query}\"",
            color = DoctaColors.outline,
            fontSize = 19.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Manrope
        )
        SmallSecondaryButton(
            text = stringResource(Res.string.cancel),
            iconRes = Res.drawable.close_icon,
            onClick = onCancel
        )
    }
}

@Composable
private fun CourseSearchSearchedCourseComponent(
    state: CourseSearchState.SearchedCourse,
    onTryAgain: () -> Unit,
    onAddCourse: (Course) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        state.course?.let {
            CourseComponent(course = it)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallSecondaryButton(
                text = stringResource(Res.string.try_again),
                iconRes = Res.drawable.reset_icon,
                onClick = onTryAgain
            )
            state.course?.let {
                SmallPrimaryButton(
                    text = stringResource(Res.string.add),
                    iconRes = Res.drawable.add_icon,
                    onClick = { onAddCourse(it) }
                )
            }
        }
    }
}