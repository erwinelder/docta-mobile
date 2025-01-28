package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.open_answer_question_instructions

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun OpenAnswerQuestionScreenPreview() {
    ScreenPreviewContainer(appTheme = AppTheme.Light) {
        OpenAnswerQuestionScreen(
            screenPadding = PaddingValues(),
            questionInstructionsRes = Res.string.open_answer_question_instructions,
            questionText = "What is the capital of France?",
            answerInput = "",
            onAnswerChange = {},
            checkIsAllowed = true,
            checkResult = null,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}