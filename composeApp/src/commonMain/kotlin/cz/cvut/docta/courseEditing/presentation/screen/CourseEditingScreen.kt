package cz.cvut.docta.courseEditing.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.button.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.field.LargeTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CourseEditingScreen(
    onNavigateBack: () -> Unit,
    courseName: String,
    onNameChange: (String) -> Unit,
    courseLocale: String,
    onLocaleChange: (String) -> Unit,
    sections: List<SectionDraft>,
    onSectionClick: (Int) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        GlassSurfaceTopBackNavButton(
            text = stringResource(SharedRes.strings.edit_course),
            onClick = onNavigateBack
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                .weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                LargeTextField(
                    text = courseName,
                    onValueChange = onNameChange,
                    fontSize = 18.sp
                )
                LargeTextField(
                    text = courseLocale,
                    onValueChange = onLocaleChange,
                    fontSize = 18.sp
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = sections) { section ->
                    GlassSurfaceNavigationButton(
                        text = section.name,
                        padding = PaddingValues(
                            start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
                        ),
                        cornerSize = 18.dp,
                        onClick = {
                            onSectionClick(section.id)
                        }
                    )
                }
            }
        }
        PrimaryButton(
            text = stringResource(SharedRes.strings.save),
            onClick = onSaveButtonClick
        )
    }
}