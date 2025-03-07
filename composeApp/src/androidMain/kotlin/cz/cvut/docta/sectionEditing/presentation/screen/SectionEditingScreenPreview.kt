package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.LessonDraft

@Preview(device = PIXEL_7_PRO)
@Composable
fun SectionEditingScreenPreview() {
    ScreenPreviewContainer(appTheme = AppTheme.Light) {
        SectionEditingScreen(
            onNavigateBack = {},
            sectionName = "Section name",
            onNameChange = {},
            onSaveButtonClick = {},
            lessons = listOf(
                LessonDraft.Test(
                    id = 1,
                    name = "Lesson name"
                ),
                LessonDraft.Test(
                    id = 2,
                    name = "Lesson prename"
                ),
                LessonDraft.Test(
                    id = 3,
                    name = "Lesson lastname"
                )
            ),
            onLessonClick = {}
        )
    }
}