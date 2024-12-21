package cz.cvut.docta.course.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.cvut.docta.course.domain.model.CourseLightweight

@Composable
fun CoursesScreen(
    courseList: List<CourseLightweight>
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
    ) {
        items(items = courseList) { course ->
            Text(
                text = course.code
            )
            Text(
                text = course.locale
            )
            Text(
                text = course.name
            )
        }
    }
}
