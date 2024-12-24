package cz.cvut.docta.section.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.section.domain.model.CourseSection

@Composable
fun CourseSectionsScreen(
    onBackButtonClick: () -> Unit,
    courseSectionList: List<CourseSection>,
    onSectionClick: (CourseSection) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScreenContainerWithBackNavButton(
        onBackButtonClick = onBackButtonClick,
        padding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = courseSectionList) { section ->
                GlassSurfaceNavigationButton(
                    text = section.name,
                    padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    onClick = {
                        onSectionClick(section)
                    }
                )
            }
        }
    }
}