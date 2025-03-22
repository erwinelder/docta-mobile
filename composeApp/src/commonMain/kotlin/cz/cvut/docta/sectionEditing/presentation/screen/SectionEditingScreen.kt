package cz.cvut.docta.sectionEditing.presentation.screen

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
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.field.LargeTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.lesson.domain.model.LessonDraft
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SectionEditingScreen(
    onNavigateBack: () -> Unit,
    sectionName: String,
    onNameChange: (String) -> Unit,
    lessons: List<LessonDraft>,
    onLessonClick: (Int) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        GlassSurfaceTopBackNavButton(
            text = stringResource(SharedRes.strings.edit_section),
            onClick = onNavigateBack
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                .weight(1f)
        ) {
            LargeTextField(
                text = sectionName,
                onValueChange = onNameChange,
                fontSize = 18.sp
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = lessons) { lesson ->
                    GlassSurfaceNavigationButton(
                        text = lesson.name,
                        padding = PaddingValues(
                            start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
                        ),
                        cornerSize = 18.dp,
                        onClick = {
                            onLessonClick(lesson.id)
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