package cz.cvut.docta.lessonSession.presentation.screen.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lessonSession.domain.model.CompletedLessonStats
import cz.cvut.docta.lessonSession.presentation.model.LessonStatsUiState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun LessonResultsScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    lessonStatsUiState: LessonStatsUiState = LessonStatsUiState.fromStats(
        stats = CompletedLessonStats(
            percentage = 98.0,
            points = 0.0,
            mistakeCount = 0
        )
    )
) {
    var showStats by remember { mutableStateOf(true) }

    ScreenPreviewContainer(appTheme = appTheme) {
        LessonResultsScreen(
            requestState = null,
            lessonStatsUiState = lessonStatsUiState.takeIf { showStats },
            onContinueButtonClick = { showStats = !showStats },
            onErrorClose = {}
        )
    }
}