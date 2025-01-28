package cz.cvut.docta.section.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.section.domain.model.SectionWithStatistics
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun CourseSectionsScreen(
    courseName: String,
    courseIconRes: DrawableResource? = null,
    onNavigateBack: () -> Unit,
    sectionList: List<SectionWithStatistics>,
    onSectionClick: (SectionWithStatistics) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GlassSurfaceTopBackNavButton(
                text = courseName,
                iconRes = courseIconRes,
                onClick = onNavigateBack
            )
        }
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items = sectionList) { section ->
                GlassSurfaceNavigationButton(
                    text = section.name,
                    filledWidths = FilledWidthByScreenType(),
                    padding = PaddingValues(start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                    cornerSize = 20.dp,
                    onClick = {
                        onSectionClick(section)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}