package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.LessonDraft

@Preview(device = PIXEL_7_PRO)
@Composable
fun SectionEditingScreenPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        SectionEditingScreen(
            onNavigateBack = {},
            sectionName = "Section name",
            onNameChange = {},
            onSaveButtonClick = {},
            lessons = listOf(
                LessonDraft.Test(
                    id = 1,
                    name = "Lesson 1 name",
                    description = "Lesson 1 description"
                ),
                LessonDraft.Test(
                    id = 2,
                    name = "Lesson 2 name",
                    description = "Lesson 2 description"
                ),
                LessonDraft.Test(
                    id = 3,
                    name = "Lesson 3 name",
                    description = "Lesson 3 description"
                )
            ),
            onLessonClick = {}
        )
    }
}