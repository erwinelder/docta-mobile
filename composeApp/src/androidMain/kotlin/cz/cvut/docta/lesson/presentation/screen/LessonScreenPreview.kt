package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonStatistics

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun LessonScreenPreview() {
    ScreenPreviewContainer {
        LessonScreen()
    }
}