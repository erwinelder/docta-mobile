package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.field.DoctaTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDraft
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.edit_section
import docta.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
fun SectionEditingScreen(
    onNavigateBack: () -> Unit,
    sectionName: String,
    onNameChange: (String) -> Unit,
    onSaveButtonClick: () -> Unit,
    lessons: List<LessonDraft>,
    onLessonClick: (Long) -> Unit
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        GlassSurfaceTopBackNavButton(
            text = stringResource(Res.string.edit_section),
            onClick = onNavigateBack
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            DoctaTextField(
                text = sectionName,
                onValueChange = onNameChange,
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
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
            text = stringResource(Res.string.save),
            onClick = onSaveButtonClick
        )
    }
}