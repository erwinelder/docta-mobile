package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.model.question.Materials

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun OpenAnswerQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "What is the capital of France?",
    answerInput: String = "The capital of France is Paris",
    checkIsAllowed: Boolean = true,
    checkResult: QuestionCheckResult? = null
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        OpenAnswerQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            questionMaterials = listOf(
                Materials(id = 1, text = "Materials text of 1."),
                Materials(id = 2, text = "Materials text of 2, which is longer."),
                Materials(id = 3, text = "Materials text of 3, which is much much longer than in first one."),
                Materials(id = 4, text = "Materials text of 4, which should be the longest of them all, longer than in first and second one.")
            ),
            answerInput = answerInput,
            onAnswerChange = {},
            checkIsAllowed = checkIsAllowed,
            checkResult = checkResult,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}