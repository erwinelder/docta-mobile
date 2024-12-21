package cz.cvut.docta.course.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.components.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.components.containers.GlassSurface
import cz.cvut.docta.core.presentation.components.screenContainers.ScreenContainer
import cz.cvut.docta.course.domain.model.CourseLightweight

@Composable
fun CoursesScreen(
    courseList: List<CourseLightweight>
) {
    val lazyListState = rememberLazyListState()

    ScreenContainer(
        padding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = courseList) { course ->
                GlassSurfaceNavigationButton(
                    text = course.name,
                    padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    onClick = {}
                )
            }
        }
    }
}
