package cz.cvut.docta.section.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainerWithBackNavButton
import cz.cvut.docta.section.domain.model.SectionWithProgress
import cz.cvut.docta.section.presentation.component.SectionWithProgressComponent
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun CourseSectionsScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    courseName: String,
    courseIconRes: DrawableResource? = null,
    onNavigateBack: () -> Unit,
    sections: List<SectionWithProgress>,
    onSectionClick: (SectionWithProgress) -> Unit
) {
    val lazyListState = rememberLazyListState()

    ScreenContainerWithBackNavButton(
        onBackButtonClick = onNavigateBack,
        backButtonText = courseName,
        backButtonIconRes = courseIconRes,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        screenPadding = screenPadding,
        padding = PaddingValues(top = 8.dp),
        contentFilledWith = FilledWidthByScreenType()
    ) {
        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items = sections) { section ->
                SectionWithProgressComponent(
                    section = section,
                    filledWidths = null,
                    onClick = onSectionClick
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}