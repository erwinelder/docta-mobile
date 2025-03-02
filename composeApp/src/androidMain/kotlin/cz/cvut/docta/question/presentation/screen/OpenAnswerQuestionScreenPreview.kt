package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun OpenAnswerQuestionScreenPreview() {
    ScreenPreviewContainer(appTheme = AppTheme.Light) {
        OpenAnswerQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = "What is the capital of France?",
            answerInput = "The capital of France is Paris",
            onAnswerChange = {},
            checkIsAllowed = true,
            checkResult = null,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}