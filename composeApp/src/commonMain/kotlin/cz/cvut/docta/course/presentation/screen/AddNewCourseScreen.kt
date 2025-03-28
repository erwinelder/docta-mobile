package cz.cvut.docta.course.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.SmallPrimaryButton
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.field.LargeTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.course.domain.mapper.toCourse
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.presentation.component.CourseComponent
import cz.cvut.docta.course.presentation.model.CourseSearchState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.add_icon
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.reset_icon
import docta.composeapp.generated.resources.search_icon

@Composable
fun AddNewCourseScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    searchedCourseState: CourseSearchState,
    query: String,
    onQueryChange: (String) -> Unit,
    searchIsAllowed: Boolean,
    onSearch: () -> Unit,
    onSearchCancel: () -> Unit,
    onTryAgain: () -> Unit,
    onAddCourse: (CourseWithProgress) -> Unit
) {
    ScreenContainerWithBackNavButton(
        screenPadding = screenPadding,
        verticalArrangement = Arrangement.Center,
        onNavigateBack = onNavigateBack,
        backButtonText = stringResource(SharedRes.strings.add_new_course)
    ) {
        AnimatedContent(
            targetState = searchedCourseState,
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
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
            placeholderText = stringResource(SharedRes.strings.course_search_placeholder),
            onValueChange = onQueryChange
        )
        SmallPrimaryButton(
            text = stringResource(SharedRes.strings.search),
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
            text = stringResource(SharedRes.strings.searching_for_course, state.query),
            color = DoctaColors.outline,
            fontSize = 19.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Manrope
        )
        SmallSecondaryButton(
            text = stringResource(SharedRes.strings.cancel),
            iconRes = Res.drawable.close_icon,
            onClick = onCancel
        )
    }
}

@Composable
private fun CourseSearchSearchedCourseComponent(
    state: CourseSearchState.SearchedCourse,
    onTryAgain: () -> Unit,
    onAddCourse: (CourseWithProgress) -> Unit
) {
    val course = state.course?.toCourse()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (course != null) {
            CourseComponent(course = course)
        } else {
            Text(
                text = stringResource(SharedRes.strings.course_with_code_not_found, state.query),
                color = DoctaColors.onSurface,
                fontSize = 19.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Manrope
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallSecondaryButton(
                text = stringResource(SharedRes.strings.try_again),
                iconRes = Res.drawable.reset_icon,
                onClick = onTryAgain
            )
            state.course?.let { course ->
                SmallPrimaryButton(
                    text = stringResource(SharedRes.strings.add),
                    iconRes = Res.drawable.add_icon,
                    onClick = { onAddCourse(course) }
                )
            }
        }
    }
}